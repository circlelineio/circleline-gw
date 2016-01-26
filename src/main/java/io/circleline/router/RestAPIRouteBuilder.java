package io.circleline.router;

import io.circleline.filter.FilterFactory;
import io.circleline.message.ApiEndpoint;
import org.apache.camel.Processor;
import org.apache.camel.model.RouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
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
        final FilterFactory filterFactory = FilterFactory.getInstance();
        Iterator<ApiEndpoint> pathIterator = apiEndpoints.iterator();

        while (pathIterator.hasNext()) {
            ApiEndpoint apiEndpoint = pathIterator.next();
            LOG.info("API Endpoint {}", apiEndpoint);
            RouteDefinition routeDefinition = from(apiEndpoint.getFromUrl());
            for(Processor processor:processors){
                routeDefinition = routeDefinition.process(processor);
            }

            if(apiEndpoint.isRateLimit()){
                routeDefinition = routeDefinition.process(filterFactory.rateLimitFilter(null));
            }

            routeDefinition.to(apiEndpoint.getToUrl());
        }
    }
}
