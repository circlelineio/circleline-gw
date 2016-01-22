package io.circleline.filter.ratelimit;

import com.google.common.collect.Lists;
import io.circleline.message.ApiEndpoint;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitChecker {
    private Map<String,RateLimitInfo> rateLimitInfos = new ConcurrentHashMap();
    private List<OverRateLimitListener> listeners = Lists.newArrayList();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public RateLimitChecker() {
        startScheduler();
    }

    public void addOverRateLimiterListener(OverRateLimitListener listener){
        listeners.add(listener);
    }

    public boolean removeOverRateLimitListener(OverRateLimitListener listener){
        return listeners.remove(listener);
    }

    public void addRateLimitApi(ApiEndpoint api){
        if(rateLimitInfos.containsKey(api.getFromUrl())){
            rateLimitInfos.remove(api.getFromUrl());
        }
        rateLimitInfos.put(api.getFromUrl(),
                new RateLimitInfo(
                        api.getFromUrl(),
                        api.getRateLimit()));
    }

    public void removeRateLimitApi(String api){
        rateLimitInfos.remove(api);
    }

    public void receive(String api) {
        if(rateLimitInfos.containsKey(api))
            rateLimitInfos.get(api).increment();
    }

    private void startScheduler(){
        scheduler.schedule(new RateLimitCheckTimer(), 1, TimeUnit.SECONDS);
    }

    private void notifyRateLimit(RateLimitInfo rateLimitInfo){
        listeners.forEach(a -> a.overRateLimit(
                rateLimitInfo.getApi(),
                rateLimitInfo.currentValue()));
    }

    class RateLimitCheckTimer implements Runnable{
        public void run() {
            rateLimitInfos.values().stream()
                    .filter(r -> r.isOverRateLimit())
                    .forEach(r -> notifyRateLimit(r));
            rateLimitInfos.values().stream().forEach(r -> r.reset());
        }
    }

    class RateLimitInfo{
        private String api;
        private long rateLimit;
        private AtomicLong accumulator;

        public RateLimitInfo(String api,long rateLimit) {
            this.api = api;
            this.rateLimit = rateLimit;
            this.accumulator = new AtomicLong(0);
        }

        public String getApi() {
            return api;
        }

        public boolean isOverRateLimit(){
            return accumulator.get() >= rateLimit;
        }

        public long increment(){
            return accumulator.incrementAndGet();
        }

        public void reset(){
            accumulator.set(0);
        }

        public long currentValue(){
            return accumulator.get();
        }
    }
}
