package io.circleline.filter;

import io.circleline.filter.ratelimit.RateLimitFilter;
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

    public Processor rateLimitFilter(){
        return new RateLimitFilter();
    }

    public Processor blockFilter(){
        return new BlockFilter();
    }
}
