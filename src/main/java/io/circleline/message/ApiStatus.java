package io.circleline.message;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 1002515 on 2016. 1. 28..
 */
@Data
public class ApiStatus {
    private ApiEndpoint apiEndpoint;
    private AtomicLong transactionCount;
    private boolean blocked = false;

    public ApiStatus(ApiEndpoint apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
        this.transactionCount = new AtomicLong(0);
    }

//    public ApiEndpoint getApiEndpoint() {
//        return apiEndpoint;
//    }

    public long getTransactionCount() {
        return transactionCount.get();
    }

    public boolean isOverRateLimit(){
        return transactionCount.get() >= apiEndpoint.getRateLimit();
    }

    public long incrementTransactionCount(){
        return transactionCount.incrementAndGet();
    }

    public void resetTransactionCount(){
        transactionCount.set(0);
    }

    public void block(){
        this.blocked = true;
    }

    public void unblock(){
        this.blocked = false;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setTransactionCount(long transactionCount) {
        this.transactionCount.set(transactionCount);
    }
}
