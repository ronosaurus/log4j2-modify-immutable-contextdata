log4j2 filter to allow small edits to ContextData during Filter pipeline processing. For example a message
may be logged with a _correlationId_ MDC/ContextData value that needs enhanced to become _CID_:

```
<Console name="Console" target="SYSTEM_OUT">
    <!-- makes slight modification to existing MDC element -->
    <ContextDataPutCID />
    <!-- %X outputs all MDC elements -->.
    <PatternLayout pattern="%d [%t] %-5level %c{2} '%X' - %msg%n"/>
</Console>
```

```
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
```

*DANGER*, code depends on implementation details of log4j2 and is not expected to work with custom ContextData injectors

- https://logging.apache.org/log4j/2.x/manual/extending.html
- https://logging.apache.org/log4j/2.x/log4j-core/apidocs/org/apache/logging/log4j/core/impl/ContextDataInjectorFactory.html