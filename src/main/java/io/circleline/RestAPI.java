package io.circleline;

import io.circleline.common.StatusManager;
import io.circleline.filter.*;
import io.circleline.filter.error.*;
import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiStatusManagerFactory;
import io.circleline.message.ApiStatusManager;
import io.circleline.router.RestAPIRouteBuilder;
import lombok.Data;
import org.apache.camel.builder.RouteBuilder;

import java.net.ConnectException;
import java.util.List;

/**
 * Configuration 정보를 기반으로 Camel에 필요한 정보(RouteBuilder)를 생성한다.
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

    /**
     * Configuration 정보를 기반으로 Camel RouteBuilder를 생성한다.
     *
     * @return camel RouteBuilder
     */
    public RouteBuilder routeBuilder(){
        final FilterFactory ff = FilterFactory.getInstance();

        //TODO Configuration 정보를 기반으로 local-memory, imdb, jdbc 등등을 결정해서 반환하는 Factory로 구현필요.
        ApiStatusManager apiStatusManager = ApiStatusManagerFactory.getInstance(apiEndpoints)
                .getApiStatusManager(StatusManager.LOCAL);

        return RestAPIRouteBuilder.routes(apiEndpoints)
                // add Processor
                .with(ff.blockFilter(apiStatusManager))
                .with(ff.blackListFilter(blackList))
                .with(ff.rateLimitFilter(apiStatusManager))
                // add ErrorHandler TODO ErrorHandler로 Factory로 생성?
                .withError(BlockedApiException.class, new UnauthorizedErrorHandler())
                .withError(BlackListIpException.class, new UnauthorizedErrorHandler())
                .withError(ConnectException.class, new ConnectToErrorHandler())
                .withError(Exception.class,new DefaultErrorHandler());
    }
}
