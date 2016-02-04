package io.circleline.filter.ratelimit;

import io.circleline.common.Const;
import io.circleline.message.ApiEndpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitFilter implements Processor {

    private RateLimitChecker rateLimitChecker;

    public RateLimitFilter(RateLimitChecker rateLimitChecker){
        this.rateLimitChecker = rateLimitChecker;
    }
    /**
     * @param exchange
     * @throws Exception
     */
    public void process(Exchange exchange) throws Exception {
        ApiEndpoint apiEndpoint = exchange.getProperty(Const.API_ENDPOINT, ApiEndpoint.class);
        rateLimitChecker.incrementTransactionCount(apiEndpoint);
    }
}

