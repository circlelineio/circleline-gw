package io.circleline.message;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public interface ApiStatusManager{
    public void block(ApiEndpoint apiEndpoint);
    public void unblock(ApiEndpoint apiEndpoint);
    public boolean isBlocked(ApiEndpoint apiEndpoint);
    public void incrementTransactionCount(ApiEndpoint apiEndpoint);
    public void resetTransactionCount();
    public void resetTransactionCount(ApiEndpoint apiEndpoint);
    public void checkRateLimitAndBlock();
}
