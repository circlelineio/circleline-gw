package io.circleline.message;

import java.util.List;

/**
 * JDBC를 통해 ApiEndpointStatus를 저장관리한다.
 */
public class JdbcApiStatus implements ApiStatusManager {

    public JdbcApiStatus(ApiEndpoint apiEndpoint){

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
