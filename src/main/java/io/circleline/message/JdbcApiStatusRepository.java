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
        throw new UnsupportedOperationException("");
    }

    @Override
    public ApiStatus getApiStatus(ApiEndpoint apiEndpoint) {
        throw new UnsupportedOperationException("");
    }
}
