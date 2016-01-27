package io.circleline.message;

import lombok.Data;

/**
 * API Gateway 경로 정보
 */
@Data
public class ApiEndpoint {
    private static final Long DEFAULT_RATELIMIT = 0l;

    private String from;
    private String to;
    private String fromUrl;
    private String toUrl;
    private Long rateLimit;

    public ApiEndpoint(String from, String to, Long rateLimit) {
        URLBuilder builder = URLBuilder.build(from,to);
        this.from = from;
        this.to = to;
        this.fromUrl = builder.fromUrl();
        this.toUrl = builder.toUrl();
        this.rateLimit = rateLimit;
    }

    public boolean isRateLimit(){
        return rateLimit>0;
    }
}