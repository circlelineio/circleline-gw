package io.circleline.message;

import java.util.List;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public interface ApiStatusRepository {
    public void initRepository();
    public List<ApiStatus> allApiStatus();
    public ApiStatus getApiStatus(ApiEndpoint apiEndpoint);
    public void persist(ApiStatus apiStatus);
}
