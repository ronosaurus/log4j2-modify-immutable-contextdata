package com.ronosaurus.log4j2;

import com.ronosaurus.log4j2.filter.ContextDataPutValueFilter;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.*;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

@Plugin(name = "ContextDataPutCID", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE)
public class ContextDataPutCIDFilter extends ContextDataPutValueFilter {
    @Override
    protected void filter(ReadOnlyStringMap contextData) {
        String correlationId = contextData.getValue("correlationId");
        putValue(contextData, "CID", correlationId.toUpperCase()); // correlation id
    }

    @PluginFactory
    public static ContextDataPutCIDFilter createFilter() {
        return new ContextDataPutCIDFilter();
    }
}
