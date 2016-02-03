package io.circleline.message;

import java.util.List;

/**
 * JDBC를 통해 ApiEndpointStatus를 저장관리한다.
 */
public class JdbcApiStatusRepository implements ApiStatusRepository {

    public JdbcApiStatusRepository(){}

    public JdbcApiStatusRepository(List<ApiEndpoint> apiEndpoints){
    }

    @Override
    public List<ApiStatus> allApiStatus() {
        //TODO EntityManager를 통해 queryList를 한다.
        throw new UnsupportedOperationException("");
    }

    @Override
    public ApiStatus getApiStatus(ApiEndpoint apiEndpoint) {
        //TODO EntityManager를 통해 query한다.
        throw new UnsupportedOperationException("");
    }

    @Override
    public void persist(ApiStatus apiStatus) {
        //TODO EntityManager를 통해 apiStatus를 persist를 한다.
    }

    @Override
    public void initRepository() {

    }
}
