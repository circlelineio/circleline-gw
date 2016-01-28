package io.circleline.message;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 1002515 on 2016. 1. 28..
 */
public class LocalApiStatus implements ApiStatus {
    private ApiEndpoint apiEndpoint;
    private AtomicLong transactionCount;
    private boolean blocked = false;

    public LocalApiStatus(ApiEndpoint apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
        this.transactionCount = new AtomicLong(0);
    }

    @Override
    public ApiEndpoint getApiEndpoint() {
        return apiEndpoint;
    }

    @Override
    public long getTransactionCount() {
        return transactionCount.get();
    }

    @Override
    public boolean isOverRateLimit(){
        return transactionCount.get() >= apiEndpoint.getRateLimit();
    }

    @Override
    public long incrementTransactionCount(){
        return transactionCount.incrementAndGet();
    }

    @Override
    public void resetTransactionCount(){
        transactionCount.set(0);
    }

    @Override
    public void block(){
        this.blocked = true;
    }

    @Override
    public void unblock(){
        this.blocked = false;
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public void setTransactionCount(long transactionCount) {
        this.transactionCount.set(transactionCount);
    }
}
