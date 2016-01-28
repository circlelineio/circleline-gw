package io.circleline.message;

import io.circleline.common.StatusRepositoryType;

import java.util.List;

public class ApiStatusManagerFactory {
    private List<ApiEndpoint> apiEndpoints;
    private ApiStatusManager apiStatusManager;

    private static ApiStatusManagerFactory instance;

    private ApiStatusManagerFactory(List<ApiEndpoint> apiEndpoints, StatusRepositoryType type){
        this.apiEndpoints = apiEndpoints;
        this.apiStatusManager = initApiStatusManager(type);
    }

    public static ApiStatusManagerFactory createApiStatusManagerFactory(List<ApiEndpoint> apiEndpoints, StatusRepositoryType repoType){
        if(instance==null) {
            instance = new ApiStatusManagerFactory(apiEndpoints,repoType);
        }
        return instance;
    }

    private ApiStatusManager initApiStatusManager(StatusRepositoryType type){
        switch (type){
            case LOCAL: return new LocalApiStatusManager(apiEndpoints);
            case IMDB: return new JdbcApiStatus();
            case JDBC: throw new IllegalArgumentException("not implemented type");
            default: return new LocalApiStatusManager(apiEndpoints);
        }
    }

    public ApiStatusManager getApiStatusManager(){
        return apiStatusManager;
    }
}
