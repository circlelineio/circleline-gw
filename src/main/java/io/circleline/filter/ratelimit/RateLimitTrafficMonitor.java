package io.circleline.filter.ratelimit;

import io.circleline.filter.AbstractTrafficMonitor;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitTrafficMonitor extends AbstractTrafficMonitor {

    private RateLimitChecker rateLimitChecker;

    public RateLimitTrafficMonitor(RateLimitChecker rateLimitChecker){
        this.rateLimitChecker = rateLimitChecker;
    }

    public RateLimitChecker getRateLimitChecker(){
        return rateLimitChecker;
    }

    @Override
    protected void receive(String api) {
        rateLimitChecker.receive(api);
    }
}