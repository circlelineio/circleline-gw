package io.circleline.filter.ratelimit;

import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiStatus;
import io.circleline.message.ApiStatusManager;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitChecker {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ApiStatusManager apiStatusManager;

    public RateLimitChecker(ApiStatusManager apiStatusManager,long delay, TimeUnit timeUnit) {
        this.apiStatusManager = apiStatusManager;
        startScheduler(delay,timeUnit);
    }

    public void incrementTransactionCount(ApiEndpoint apiEndpoint){
        apiStatusManager.getApiStatus(apiEndpoint).incrementTransactionCount();
    }

    private void startScheduler(long delay, TimeUnit timeUnit) {
        scheduler.schedule(new RateLimitCheckTimer(), delay, timeUnit);
    }

    class RateLimitCheckTimer implements Runnable {
        public void run() {
            List<ApiStatus> statuses = apiStatusManager.allApiStatus();

            statuses.stream()
                    .filter(s -> s.isOverRateLimit())
                    .forEach(s -> s.block());

            statuses.stream()
                    .forEach(s -> s.resetTransactionCount());
        }
    }
}
