package io.circleline.message;

import io.circleline.common.StatusManager;

import java.util.List;

/**
 * Created by 1002515 on 2016. 1. 26..
 */
public class ApiStatusManagerFactory {
    private List<ApiEndpoint> apiEndpoints;

    private static ApiStatusManagerFactory instance;

    private ApiStatusManagerFactory(List<ApiEndpoint> apiEndpoints){
        this.apiEndpoints=apiEndpoints;
    }
    public static ApiStatusManagerFactory getInstance(List<ApiEndpoint> apiEndpoints){
        if(instance==null) {
            instance = new ApiStatusManagerFactory(apiEndpoints);
        }
        return instance;
    }

    public ApiStatusManager getApiStatusManager(StatusManager type){
        switch (type){
            case LOCAL: return new LocalApiStatusManager(apiEndpoints);
            case IMDB: return new JdbcApiStatusManager();
            case JDBC: throw new IllegalArgumentException("not implemented type");
            default: return new LocalApiStatusManager(apiEndpoints);
        }
    }

}
