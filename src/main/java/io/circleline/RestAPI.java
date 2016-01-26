package io.circleline;

import io.circleline.filter.FilterFactory;
import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiEndpointStatusManager;
import io.circleline.message.LocalApiEndpointStatusManager;
import io.circleline.router.RestAPIRouteBuilder;
import lombok.Data;
import org.apache.camel.builder.RouteBuilder;

import java.util.List;

/**
 * Created by 1001923 on 16. 1. 22..
 */
@Data
public class RestAPI {
    private static final Long DEFAULT_RATELIMIT = 0l;

    private List<ApiEndpoint> apiEndpoints;
    private Long rateLimit;
    private List<String> blackList;

    public RestAPI(Configuration config){
        this(config.apiList(), config.rateLimit(), config.blackList());
    }

    public RestAPI(List<ApiEndpoint> apiEndpoints, Long rateLimit, List<String> blackList){
        this.apiEndpoints=apiEndpoints;
        this.rateLimit=rateLimit;
        this.blackList=blackList;
    }

    public RouteBuilder routeBuilder(){
        final FilterFactory filterFactory = FilterFactory.getInstance();

        ApiEndpointStatusManager apiEndpointStatusManager = new LocalApiEndpointStatusManager(apiEndpoints);

        return RestAPIRouteBuilder.routes(apiEndpoints)
                .with(filterFactory.blockFilter(apiEndpointStatusManager))
                .with(filterFactory.blackListFilter(blackList))
                .with(filterFactory.rateLimitFilter(apiEndpointStatusManager));
    }
}
