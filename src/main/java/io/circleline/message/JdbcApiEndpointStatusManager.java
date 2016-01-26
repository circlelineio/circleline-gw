package io.circleline.message;

/**
 * JDBC를 통해 ApiEndpointStatus를 저장관리한다.
 */
public class JdbcApiEndpointStatusManager implements ApiEndpointStatusManager {
    @Override
    public void block(ApiEndpoint apiEndpoint) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unblock(ApiEndpoint apiEndpoint) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBlocked(ApiEndpoint apiEndpoint) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void incrementTransactionCount(ApiEndpoint apiEndpoint) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetTransactionCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetTransactionCount(ApiEndpoint apiEndpoint) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkRateLimitAndBlock() {
        throw new UnsupportedOperationException();
    }
}
