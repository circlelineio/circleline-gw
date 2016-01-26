package io.circleline.router;

import io.circleline.common.Const;
import io.circleline.message.ApiEndpoint;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by 1001923 on 16. 1. 25..
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
    public void configure() throws Exception {
        apiEndpoints.forEach(apiEndpoint -> {

            ProcessorDefinition pd =
                    from(apiEndpoint.getFromUrl())
                        .setProperty(Const.API_ENDPOINT).constant(apiEndpoint);

            for (Processor processor : processors) {
                pd = pd.process(processor);
            }

            pd.to(apiEndpoint.getToUrl());
            LOG.info("API Endpoint {}", apiEndpoint);
        });
    }
}
