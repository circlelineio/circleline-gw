package io.circleline.router;

import io.circleline.Configuration;
import io.circleline.message.ApiEndpoint;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * API Gateway Router
 */
public class LogRouter extends RouteBuilder {
    static Logger LOG = LoggerFactory.getLogger(LogRouter.class);

    private Configuration config;

    public LogRouter(Configuration config){
        this.config = config;
    }

    /**
     * API Gateway 설정정보를 기반으로 Camel Route 구성.
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        Iterator<ApiEndpoint> pathIterator = config.apiList().iterator();
        while (pathIterator.hasNext()) {
            ApiEndpoint api = pathIterator.next();
            LOG.info("API Path {}", api);
            //camel dsl
            from(api.getFromUrl())
                    .to(api.getToUrl());
        }
    }
}
