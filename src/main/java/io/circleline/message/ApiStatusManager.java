package io.circleline.message;

import java.util.List;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public interface ApiStatusManager {
    public List<ApiStatus> allApiStatus();
    public ApiStatus getApiStatus(ApiEndpoint apiEndpoint);
}
