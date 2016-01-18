package io.circleline.filter.ratelimit;

import io.circleline.Configuration;
import io.circleline.filter.FilterFactory;
import io.circleline.message.ApiPath;
import org.apache.camel.Processor;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitFilterFactory implements FilterFactory {

    private static final RateLimitFilterFactory processorFactory = new RateLimitFilterFactory();
    private RateLimitFilterFactory(){}

    public static FilterFactory getInstance(){
        return processorFactory;
    }

    @Override
    public Processor getFilter(Configuration conf, ApiPath apiPath){
        //TODO Singleton으로 관리필요.
        RateLimitChecker rateLimitChecker = new RateLimitChecker();
        rateLimitChecker.addRateLimitApi(apiPath);
        return new RateLimitFilter(
                new RateLimitTrafficMonitor(
                        rateLimitChecker
                ));
    }
}
