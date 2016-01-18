package io.circleline.filter.ratelimit;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public interface OverRateLimitListener {
    public void overRateLimit(String api, long transactions);
}
