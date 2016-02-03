package io.circleline.message;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Default ApiEndpointStatusManager
 * <p>
 * 기본으로 API의 상태를 로컬메모리에서 관리한다.
 * Singlemode에서는 이 Manager를 사용하며
 * Clustermode일 경우에는 ClusterApiEndpointStatusManager를 사용한다.
 */
public class JCacheApiStatusRepository implements ApiStatusRepository {

    private Cache<ApiEndpoint, ApiStatus> cache;
    private Set<ApiEndpoint> apiEndpointSet;

    public JCacheApiStatusRepository(List<ApiEndpoint> apiEndpoints) {
        apiEndpointSet = apiEndpoints.stream().collect(Collectors.toSet());
        initCache();
        putAllApiStatus(apiEndpoints);
    }

    /**
     * 캐쉬를 초기화한다.
     */
    private void initCache(){
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        // TODO 만기설정 확인필요. 0로
        // TODO 전략설정 확인필요. 어인떤 값이 어떤 식으로 유지되는지.
        MutableConfiguration<ApiEndpoint, ApiStatus> configuration = new MutableConfiguration();
        cache = manager.createCache("cache", configuration);
    }

    /**
     * ApiEndpoint의  상태를 캐쉬에 저장한다.
     * @param apiEndpoints
     */
    private void putAllApiStatus(List<ApiEndpoint> apiEndpoints){
        apiEndpoints.forEach(a -> cache.put(a,new ApiStatus(a)));
    }

    @Override
    public List<ApiStatus> allApiStatus(){
        //TODO 다른 방법 찾기
        return new ArrayList(cache.getAll(apiEndpointSet).values());
    }

    @Override
    public ApiStatus getApiStatus(ApiEndpoint apiEndpoint){
        return cache.get(apiEndpoint);
    }

    @Override
    public void persist(ApiStatus apiStatus) {
        cache.put(apiStatus.getApiEndpoint(), apiStatus);
    }
}
