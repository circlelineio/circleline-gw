package io.circleline.filter.ratelimit;

import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiEndpointStatusManager;
import io.circleline.router.StaticRouteBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitFilter implements Processor {

    private RateLimitChecker rateLimitChecker;

    public RateLimitFilter(ApiEndpointStatusManager apiEndpointStatusManager){
        this.rateLimitChecker = new RateLimitChecker(apiEndpointStatusManager);
    }

    /**
     *
     * @param exchange
     * @throws Exception
     */
    public void process(Exchange exchange) throws Exception {
        ApiEndpoint apiEndpoint = (ApiEndpoint)exchange.getProperty(StaticRouteBuilder.API_ENDPOT);
        rateLimitChecker.incrementTransactionCount(apiEndpoint);
    }
}

