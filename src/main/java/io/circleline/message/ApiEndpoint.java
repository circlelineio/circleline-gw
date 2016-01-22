package io.circleline.message;

import lombok.Data;

/**
 * API Gateway 경로 정보
 */
@Data
public class ApiEndpoint {
    private static final Long DEFAULT_RATELIMIT = 0l;

    private String listenPath;
    private String targetUrl;
    private Long rateLimit;

    public ApiEndpoint(String from, String to) {
        this.listenPath = from;
        this.targetUrl = to;
        this.rateLimit = DEFAULT_RATELIMIT;
    }

    public ApiEndpoint(String from, String to, Long rateLimit) {
        this.listenPath = from;
        this.targetUrl = to;
        this.rateLimit = rateLimit;
    }
}