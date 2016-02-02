package io.circleline.message;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 1002515 on 2016. 1. 28..
 */
@Data
public class ApiStatus implements Serializable{
    private ApiEndpoint apiEndpoint;
    private long transactionCount;
    private boolean blocked = false;

    public ApiStatus(ApiEndpoint apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
        this.transactionCount = 0L;
    }

    public ApiEndpoint getApiEndpoint() {
        return apiEndpoint;
    }

    public long getTransactionCount() {
        return transactionCount;
    }

    public boolean isOverRateLimit(){
        return transactionCount >= apiEndpoint.getRateLimit();
    }

    public long incrementTransactionCount(){
        return ++transactionCount;
    }

    public void resetTransactionCount(){
        transactionCount = 0;
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
        this.transactionCount = transactionCount;
    }
}
