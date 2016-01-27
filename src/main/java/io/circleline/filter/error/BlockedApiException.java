package io.circleline.filter.error;

/**
 * BlockedApiExcpetion
 */
public class BlockedApiException extends Exception {
    public BlockedApiException(String msg){
        super(msg);
    }
}
