package io.circleline.router;

import io.circleline.message.ApiEndpoint;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * API Gateway Router
 */
public class StaticRouter extends APIRouter {
    static Logger LOG = LoggerFactory.getLogger(StaticRouter.class);

    public static final String API_ENDPOT = "circle.apiEndpoint";

    private final List<ApiEndpoint> apiEndpoints;

    private StaticRouter(List<ApiEndpoint> apiEndpoints) {
        this.apiEndpoints = apiEndpoints;
    }

    public static APIRouter routes(List<ApiEndpoint> apiEndpoints) {
        return new StaticRouter(apiEndpoints);
    }

    @Override
    public void configure() throws Exception {
        Iterator<ApiEndpoint> pathIterator = apiEndpoints.iterator();

        while (pathIterator.hasNext()) {
            ApiEndpoint apiEndpoint = pathIterator.next();
            ProcessorDefinition pd = from(apiEndpoint.getFromUrl())
                    .setProperty(API_ENDPOT)
                    .constant(apiEndpoint);
            for (Processor processor : processors) {
                pd = pd.process(processor);
            }
            pd.to(apiEndpoint.getToUrl());

            LOG.info("API Endpoint {}", apiEndpoint);
        }
    }
}
