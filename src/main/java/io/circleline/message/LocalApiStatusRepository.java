package io.circleline.message;

import java.util.*;
import static java.util.stream.Collectors.*;

/**
 * Default ApiEndpointStatusManager
 * <p>
 * 기본으로 API의 상태를 로컬메모리에서 관리한다.
 * Singlemode에서는 이 Manager를 사용하며
 * Clustermode일 경우에는 ClusterApiEndpointStatusManager를 사용한다.
 */
public class LocalApiStatusRepository implements ApiStatusRepository {

    private Map<ApiEndpoint, ApiStatus> statusMap;

    public LocalApiStatusRepository(List<ApiEndpoint> apiEndpoints) {
        statusMap = apiEndpoints
                .stream()
                .collect(toMap(a -> a, a -> new ApiStatus(a)));
    }

    @Override
    public void initRepository() {}

    @Override
    public List<ApiStatus> allApiStatus(){
        return Collections.unmodifiableList(new ArrayList<>(statusMap.values()));
    }

    @Override
    public ApiStatus getApiStatus(ApiEndpoint apiEndpoint){
        return statusMap.get(apiEndpoint);
    }

    @Override
    public void persist(ApiStatus apiStatus) {
        statusMap.put(apiStatus.getApiEndpoint(),apiStatus);
    }
}
