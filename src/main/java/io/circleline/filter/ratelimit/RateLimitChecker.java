package io.circleline.filter.ratelimit;

import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiStatusManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitChecker {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ApiStatusManager apiStatusManager;

    public RateLimitChecker(ApiStatusManager apiStatusManager) {
        this.apiStatusManager = apiStatusManager;
        startScheduler();
    }

    public void incrementTransactionCount(ApiEndpoint apiEndpoint){
        apiStatusManager.incrementTransactionCount(apiEndpoint);
    }

    private void startScheduler() {
        scheduler.schedule(new RateLimitCheckTimer(), 1, TimeUnit.SECONDS);
    }

    class RateLimitCheckTimer implements Runnable {
        public void run() {
            apiStatusManager.checkRateLimitAndBlock();
            apiStatusManager.resetTransactionCount();
        }
    }
}
