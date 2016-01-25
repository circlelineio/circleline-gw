package io.circleline.router;

import com.google.common.collect.Lists;
import io.circleline.message.ApiEndpoint;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * Created by 1001923 on 16. 1. 25..
 */
public class RestAPIRouter extends RouteBuilder {
    static Logger LOG = LoggerFactory.getLogger(RestAPIRouter.class);

    private final List<ApiEndpoint> apiEndpoints;
    private final List<Processor> processors = Lists.newArrayList();

    private RestAPIRouter(List<ApiEndpoint> apiEndpoints){
        this.apiEndpoints=apiEndpoints;
    }

    public static RestAPIRouter routes(List<ApiEndpoint> apiEndpoints){
        return new RestAPIRouter(apiEndpoints);
    }

    public RestAPIRouter with(Processor processor){
        processors.add(processor);
        return this;
    }

    @Override
    public void configure() throws Exception {
        Iterator<ApiEndpoint> pathIterator = apiEndpoints.iterator();

        while (pathIterator.hasNext()) {
            ApiEndpoint apiEndpoint = pathIterator.next();
            LOG.info("API Endpoint {}", apiEndpoint);
            RouteDefinition routeDefinition = from(apiEndpoint.getFromUrl());
            for(Processor processor:processors){
                routeDefinition=routeDefinition.process(processor);
            }
            routeDefinition.to(apiEndpoint.getToUrl());
        }
    }
}
