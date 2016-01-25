package io.circleline.router;


import io.circleline.Configuration;
import io.circleline.message.ApiEndpoint;
import io.circleline.filter.BlackListFilterFactory;
import io.circleline.filter.ratelimit.RateLimitFilterFactory;
import io.circleline.RestAPI;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * API Gateway Router
 */
@Component
public class StaticRouter extends RouteBuilder {
    static Logger LOG = LoggerFactory.getLogger(StaticRouter.class);
    @Autowired
    private Configuration config;

    public StaticRouter(){}

    public StaticRouter(Configuration config){
        this.config = config;
    }

    /**
     * API Gateway 설정정보를 기반으로 Camel Route 구성.
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        RestAPI api = new RestAPI(config);
        Iterator<ApiEndpoint> pathIterator = api.getApiEndpoints().iterator();

        while (pathIterator.hasNext()) {
            ApiEndpoint apiEndpoint = pathIterator.next();
            LOG.info("API Endpoint {}", apiEndpoint);
            //camel dsl
            from(apiEndpoint.getFromUrl())
                    .process(RateLimitFilterFactory.getInstance().getFilter(config, apiEndpoint))
                    .process(BlackListFilterFactory.getInstance().getFilter(config, apiEndpoint))
                    .to(apiEndpoint.getToUrl());

//            RouteDefinition routeDefinition = from(apiEndpoint.getFromUrl());
//            routeDefinition = setFilters(routeDefinition,api);
//            routeDefinition.to(apiEndpoint.getToUrl());
        }
    }

//    private RouteDefinition setFilters(RouteDefinition routeDef, RestAPI api){
//        Iterator<Processor> iterator = api.getFilters().iterator();
//        RouteDefinition currentRouteDef = routeDef;
//        while(iterator.hasNext()){
//            Processor apiEndpoint = iterator.next();
//            currentRouteDef = currentRouteDef.process(apiEndpoint);
//        }
//        return currentRouteDef;
//    }

}
