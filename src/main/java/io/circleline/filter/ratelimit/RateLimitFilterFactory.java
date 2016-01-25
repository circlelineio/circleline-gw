package io.circleline.filter.ratelimit;

import io.circleline.Configuration;
import io.circleline.message.ApiEndpoint;
import org.apache.camel.Processor;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitFilterFactory {

    private static final RateLimitFilterFactory processorFactory = new RateLimitFilterFactory();
    private RateLimitFilterFactory(){}

    public static RateLimitFilterFactory getInstance(){
        return processorFactory;
    }

    public Processor getFilter(Configuration conf, ApiEndpoint apiEndpoint){
        //TODO Singleton으로 관리필요.
        RateLimitChecker rateLimitChecker = new RateLimitChecker();
        rateLimitChecker.addRateLimitApi(apiEndpoint);
        return new RateLimitFilter(
                new RateLimitTrafficMonitor(
                        rateLimitChecker
                ));
    }
}
