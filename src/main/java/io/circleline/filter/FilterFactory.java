package io.circleline.filter;

import io.circleline.filter.ratelimit.RateLimitChecker;
import io.circleline.filter.ratelimit.RateLimitFilter;
import io.circleline.filter.ratelimit.RateLimitTrafficMonitor;
import io.circleline.message.ApiEndpoint;
import org.apache.camel.Processor;

import java.util.List;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class FilterFactory {
    private static final FilterFactory filterFactory = new FilterFactory();
    private FilterFactory(){}

    public static FilterFactory getInstance(){
        return filterFactory;
    }

    public Processor blackListFilter(List<String> blackList){
        return new BlackListFilter(blackList);
    }

    public Processor rateLimitFilter(ApiEndpoint apiEndpoint){
        RateLimitChecker rateLimitChecker = new RateLimitChecker();
        rateLimitChecker.addRateLimitApi(apiEndpoint);
        return new RateLimitFilter(
                new RateLimitTrafficMonitor(
                        rateLimitChecker
                ));
    }
}
