package io.circleline.message;

import lombok.Data;

/**
 * 하나의 API Gateway 경로 정보 저장
 */
@Data
public class ApiPath{
    private String listenPath;
    private String targetUrl;
    private long rateLimit;
    private boolean bridgeEndpoint;

    public ApiPath(String from, String to) {
        this(from,to,0);
    }

    public ApiPath(String from, String to, long rateLimit) {
        this(from,to,rateLimit,true);
    }

    public ApiPath(String from, String to, long rateLimit, boolean bridgeEndpoint) {
        this.listenPath = from;
        this.targetUrl = to;
        this.rateLimit = rateLimit;
        this.bridgeEndpoint = bridgeEndpoint;
    }
}