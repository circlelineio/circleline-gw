package io.circleline.router;


import io.circleline.Configuration;
import io.circleline.message.ApiPath;
import io.circleline.filter.BlackListFilterFactory;
import io.circleline.filter.ratelimit.RateLimitFilterFactory;
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
    private boolean bridgeEndpoint;

    public StaticRouter(){}

    public StaticRouter(Configuration config){
        this.config = config;
        this.bridgeEndpoint = true;
    }

    /**
     * API Gateway 설정정보를 기반으로 Camel Route 구성.
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        Iterator<ApiPath> pathIterator = config.apiList().iterator();
        while (pathIterator.hasNext()) {
            ApiPath api = pathIterator.next();
            LOG.info("API Path {}", api);
            //camel dsl
            from(api.getListenPath())
                    .process(RateLimitFilterFactory.getInstance().getFilter(config, api))
                    .process(BlackListFilterFactory.getInstance().getFilter(config, api))
                    .to(api.getTargetUrl() + "?bridgeEndpoint=" + bridgeEndpoint);
        }
    }
}
