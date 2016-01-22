package io.circleline.message;

import lombok.Data;

/**
 * API Gateway 경로 정보
 */
@Data
public class ApiEndpoint {
    private static final Long DEFAULT_RATELIMIT = 0l;

    private String fromUrl;
    private String toUrl;
    private Long rateLimit;

    public ApiEndpoint(String from, String to) {
       this(from,to,DEFAULT_RATELIMIT);
    }

    public ApiEndpoint(String from, String to, Long rateLimit) {
        URLBuilder builder = URLBuilder.build(from,to);
        this.fromUrl = builder.fromUrl();
        this.toUrl = builder.toUrl();
        this.rateLimit = rateLimit;
    }
}