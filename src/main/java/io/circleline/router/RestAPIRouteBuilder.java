package io.circleline.router;

import io.circleline.common.Const;
import io.circleline.message.ApiEndpoint;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Camel RouteBuilder 구현체
 */
public class RestAPIRouteBuilder extends APIRouteBuilder {
    static Logger LOG = LoggerFactory.getLogger(RestAPIRouteBuilder.class);

    private final List<ApiEndpoint> apiEndpoints;
    private RestAPIRouteBuilder(List<ApiEndpoint> apiEndpoints){
        this.apiEndpoints=apiEndpoints;
    }

    public static APIRouteBuilder routes(List<ApiEndpoint> apiEndpoints){
        return new RestAPIRouteBuilder(apiEndpoints);
    }

    @Override
    protected void initErrorHandler(){
        errorHandlers.forEach((clazz,errorHandler) ->
            onException(clazz).handled(true).process(errorHandler).stop()
        );
    }

    @Override
    protected void initRoute() {
        // make route
        apiEndpoints.forEach(apiEndpoint -> {
            //from
            ProcessorDefinition pd =
                    from(apiEndpoint.getFromUrl())
                        .setProperty(Const.API_ENDPOINT).constant(apiEndpoint);
            //processor
            for (Processor processor : processors) {
                pd = pd.process(processor);
            }

            //to
            pd.to(apiEndpoint.getToUrl());
            LOG.info("API Endpoint {}", apiEndpoint);
        });
    }
}
