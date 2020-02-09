package com.ronosaurus.log4j2.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.ContextDataInjector;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.core.impl.ContextDataInjectorFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

/**
 * Busy work to route filter overloads to a single method
 */
public abstract class ContextDataFilter extends AbstractFilter {
    protected abstract Result filter();

    //region filter
    @Override
    public Result filter(final LogEvent event) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object... params) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Object msg,
                         final Throwable t) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Message msg,
                         final Throwable t) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0, final Object p1) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0, final Object p1, final Object p2) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0, final Object p1, final Object p2, final Object p3) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0, final Object p1, final Object p2, final Object p3,
                         final Object p4) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0, final Object p1, final Object p2, final Object p3,
                         final Object p4, final Object p5) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0, final Object p1, final Object p2, final Object p3,
                         final Object p4, final Object p5, final Object p6) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0, final Object p1, final Object p2, final Object p3,
                         final Object p4, final Object p5, final Object p6,
                         final Object p7) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0, final Object p1, final Object p2, final Object p3,
                         final Object p4, final Object p5, final Object p6,
                         final Object p7, final Object p8) {
        return filter();
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object p0, final Object p1, final Object p2, final Object p3,
                         final Object p4, final Object p5, final Object p6,
                         final Object p7, final Object p8, final Object p9) {
        return filter();
    }
    //endregion
}
