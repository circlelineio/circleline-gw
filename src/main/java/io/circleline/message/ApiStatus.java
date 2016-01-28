package io.circleline.message;

/**
 * Created by 1002515 on 2016. 1. 28..
 */
public interface ApiStatus {
    ApiEndpoint getApiEndpoint();
    long getTransactionCount();
    boolean isOverRateLimit();
    long incrementTransactionCount();
    void resetTransactionCount();
    void setTransactionCount(long transactionCount);
    void block();
    void unblock();
    boolean isBlocked();
}
