package io.circleline.filter.ratelimit;

import io.circleline.common.Const;
import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiStatusManager;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitFilter implements Processor {
    static Logger LOG = LoggerFactory.getLogger(RateLimitFilter.class);

    private RateLimitChecker rateLimitChecker;

    public RateLimitFilter(ApiStatusManager apiStatusManager){
        this.rateLimitChecker = new RateLimitChecker(apiStatusManager);
    }

    /**
     *
     * @param exchange
     * @throws Exception
     */
    public void process(Exchange exchange) throws Exception {

        LOG.info("RateLimitFilter " + exchange);

        ApiEndpoint apiEndpoint = exchange.getProperty(Const.API_ENDPOINT,ApiEndpoint.class);
        rateLimitChecker.incrementTransactionCount(apiEndpoint);
    }
}

