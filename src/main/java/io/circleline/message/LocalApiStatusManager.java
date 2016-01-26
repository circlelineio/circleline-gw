package io.circleline.message;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.stream.Collectors.*;

/**
 * Default ApiEndpointStatusManager
 * <p>
 * 기본으로 API의 상태를 로컬메모리에서 관리한다.
 * Singlemode에서는 이 Manager를 사용하며
 * Clustermode일 경우에는 ClusterApiEndpointStatusManager를 사용한다.
 */
public class LocalApiStatusManager implements ApiStatusManager {

    private Map<ApiEndpoint, ApiEndpointStatus> statusMap;

    public LocalApiStatusManager(List<ApiEndpoint> apiEndpoints) {
        statusMap = apiEndpoints
                .stream()
                .collect(toMap(a -> a, a -> new ApiEndpointStatus(a)));
    }

    @Override
    public void block(ApiEndpoint apiEndpoint) {
        assertManagedApiEndpoint(apiEndpoint);
        statusMap.get(apiEndpoint).block();
    }

    @Override
    public void unblock(ApiEndpoint apiEndpoint) {
        assertManagedApiEndpoint(apiEndpoint);
        statusMap.get(apiEndpoint).unblock();
    }

    @Override
    public boolean isBlocked(ApiEndpoint apiEndpoint) {
        assertManagedApiEndpoint(apiEndpoint);
        return statusMap.get(apiEndpoint).isBlocked();
    }

    @Override
    public void incrementTransactionCount(ApiEndpoint apiEndpoint) {
        assertManagedApiEndpoint(apiEndpoint);
        statusMap.get(apiEndpoint).incrementTransactionCount();
    }

    @Override
    public void resetTransactionCount() {
        statusMap.values().stream().forEach(a -> a.reset());
    }

    @Override
    public void resetTransactionCount(ApiEndpoint apiEndpoint) {
        assertManagedApiEndpoint(apiEndpoint);
        statusMap.get(apiEndpoint).reset();
    }

    @Override
    public void checkRateLimitAndBlock() {
        statusMap.values().stream()
                .filter(r -> r.isOverRateLimit())
                .forEach(a -> a.block());
    }

    private void assertManagedApiEndpoint(ApiEndpoint apiEndpoint) {
        if (!statusMap.containsKey(apiEndpoint)) {
            throw new IllegalArgumentException("not managed ApiEndpoint " + apiEndpoint);
        }
    }

    class ApiEndpointStatus {

        private ApiEndpoint apiEndpoint;
        private AtomicLong transactionCount;
        private boolean blocked = false;

        public ApiEndpointStatus(ApiEndpoint apiEndpoint) {
            this.apiEndpoint = apiEndpoint;
            this.transactionCount = new AtomicLong(0);
        }

        public ApiEndpoint getApiEndpoint() {
            return apiEndpoint;
        }

        public long getTransactionCount() {
            return transactionCount.get();
        }

        public boolean isOverRateLimit(){
            return transactionCount.get() >= apiEndpoint.getRateLimit();
        }

        public long incrementTransactionCount(){
            return transactionCount.incrementAndGet();
        }

        public void reset(){
            transactionCount.set(0);
        }

        public void block(){
            this.blocked = true;
        }

        public void unblock(){
            this.blocked = false;
        }

        public boolean isBlocked() {
            return blocked;
        }
    }
}
