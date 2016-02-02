package io.circleline.message;

import io.circleline.common.StatusRepositoryType;

import java.util.List;

/**
 * Created by 1001923 on 16. 2. 2..
 */
public class ApiStatusManager {
    private List<ApiEndpoint> apiEndpoints;
    private ApiStatusRepository apiStatusRepository;

    public ApiStatusManager(List<ApiEndpoint> apiEndpoints, StatusRepositoryType statusType){
        this.apiEndpoints=apiEndpoints;
        this.apiStatusRepository = apiStatusRepository(statusType);
    }

    private ApiStatusRepository apiStatusRepository(StatusRepositoryType statusType){
        switch (statusType){
            case LOCAL: return new LocalApiStatusRepository(apiEndpoints);
            case IMDB: return new JdbcApiStatusRepository(apiEndpoints);
            case JDBC: throw new IllegalArgumentException("not implemented type");
            default: return new LocalApiStatusRepository(apiEndpoints);
        }
    }

    public ApiStatusRepository repository(){
        return apiStatusRepository;
    }
}
