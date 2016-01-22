package io.circleline.message;

import lombok.Data;

import java.util.List;

/**
 * Created by 1001923 on 16. 1. 22..
 */
@Data
public class RestAPI {
    private static final Long DEFAULT_RATELIMIT = 0l;

    private List<ApiEndpoint> apiEndpoints;
    private Long rateLimit;

    public RestAPI(List<ApiEndpoint> apiEndpoints){
        this(apiEndpoints, DEFAULT_RATELIMIT);
    }

    public RestAPI(List<ApiEndpoint> apiEndpoints, Long rateLimit){
        this.apiEndpoints=apiEndpoints;
        this.rateLimit=rateLimit;
    }
}
