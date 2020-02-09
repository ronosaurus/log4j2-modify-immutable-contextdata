package com.ronosaurus.log4j2.filter;

import org.apache.logging.log4j.core.ContextDataInjector;
import org.apache.logging.log4j.core.impl.ContextDataInjectorFactory;
import org.apache.logging.log4j.core.util.ReflectionUtil;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.SortedArrayStringMap;
import org.apache.logging.log4j.util.StringMap;

import java.lang.reflect.Field;

/**
 * DANGER, allow MDC/ContextData to be modified during logging pipeline.
 *
 * This class depends on specific log4j2 functionality. It tests at
 * startup if it is likely to succeed and disables itself if necessary.
 *
 * Use case is for making small modifications to MDC/ContextData
 * that will not cause recursive side affects.
 *
 * This class is not meant to be used with custom ContextData factories.
 */
public abstract class ContextDataPutValueFilter extends ContextDataFilter {
    private static Field STRINGMAP_IMMUTABLE_FIELD = null;
    private static boolean ENABLED = true;

    // inspired by https://github.com/apache/logging-log4j2/blob/master/log4j-core/src/main/java/org/apache/logging/log4j/core/filter/ThreadContextMapFilter.java
    protected final ContextDataInjector injector = ContextDataInjectorFactory.createInjector();

    @Override
    public void start() {
        // testing in start() instead of static() to test actual context data implementation
        if (injector.rawContextData() instanceof SortedArrayStringMap) { // 2.7 - 2.13+ (since 2018)
            try {
                STRINGMAP_IMMUTABLE_FIELD = SortedArrayStringMap.class.getDeclaredField("immutable");
            } catch (Exception e) { // NoSuchFieldException
                StatusLogger.getLogger().warn(
                    "Disabling ContextDataPutValueFilter, " +
                    "expected ContextDataInjectorFactory.createInjector().rawContextData() " +
                    "to be SortedArrayStringMap");
                ENABLED = false;
            }
        }
        super.start();
    }

    @Override
    protected Result filter() {
        if (ENABLED) {
            filter(injector.rawContextData());
        }
        return Result.NEUTRAL;
    }

    /**
     *
     * @param contextData declared as ReadOnlyStringMap to align with out of the box definition
     */
    protected abstract void filter(ReadOnlyStringMap contextData);

    /**
     * DANGER, this method will attempt to modify the underlying immutable StringMap
     */
    protected void putValue(final ReadOnlyStringMap stringMap, String key, String value) {
        ReflectionUtil.setFieldValue(STRINGMAP_IMMUTABLE_FIELD, stringMap, false); // try
        ((StringMap)stringMap).putValue(key, value);
        ReflectionUtil.setFieldValue(STRINGMAP_IMMUTABLE_FIELD, stringMap, true); // finally
    }
}