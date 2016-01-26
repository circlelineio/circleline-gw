package io.circleline.message;

import java.util.List;

/**
 * Created by 1002515 on 2016. 1. 26..
 */
public class ApiEndpointStatusManagerFactory {

    public enum PERSIST_TYPE{
        LOCAL,IMDB,JDBC
    }

    private static ApiEndpointStatusManagerFactory instance = new ApiEndpointStatusManagerFactory();
    private ApiEndpointStatusManagerFactory(){}

    public static ApiEndpointStatusManagerFactory getInstance(){
        return instance;
    }

    public ApiEndpointStatusManager getApiEndpointStatusManager(List<ApiEndpoint> apiEndpoints, PERSIST_TYPE type){
        switch (type){
            case LOCAL: return new LocalApiEndpointStatusManager(apiEndpoints);
            case IMDB: return new JdbcApiEndpointStatusManager();
            case JDBC: throw new IllegalArgumentException("not implemented type");
            default: return new LocalApiEndpointStatusManager(apiEndpoints);
        }
    }

}
