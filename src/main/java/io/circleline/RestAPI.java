package io.circleline;

import io.circleline.common.Const;
import io.circleline.common.StatusRepositoryType;
import io.circleline.filter.*;
import io.circleline.filter.error.*;
import io.circleline.filter.ratelimit.RateLimitChecker;
import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiStatusManagerFactory;
import io.circleline.message.ApiStatusManager;
import io.circleline.router.RestAPIRouteBuilder;
import lombok.Data;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spi.Registry;

import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Configuration 정보를 기반으로 Camel에 필요한 정보(RouteBuilder)를 생성한다.
 */
@Data
public class RestAPI {
    private static final Long DEFAULT_RATELIMIT = 0l;

    private List<ApiEndpoint> apiEndpoints;
    private Long rateLimit;
    private List<String> blackList;
    private StatusRepositoryType statusType;

    public RestAPI(Configuration config){
        this(config.apiList(), config.rateLimit(), config.blackList());
        if(config.apiStatusRepository()!=null) {
            this.setStatusType(StatusRepositoryType.valueOf(config.apiStatusRepository()));
        }else{
            this.setStatusType(StatusRepositoryType.LOCAL);
        }
    }

    public RestAPI(List<ApiEndpoint> apiEndpoints, Long rateLimit, List<String> blackList){
        this.apiEndpoints = apiEndpoints;
        this.rateLimit = rateLimit;
        this.blackList = blackList;
    }

    public Registry registry(){
        ApiStatusManager apiStatus =
                ApiStatusManagerFactory.createApiStatusManagerFactory(apiEndpoints, statusType)
                .getApiStatusManager();

        RateLimitChecker rateLimitChecker = new RateLimitChecker(apiStatus, 1, TimeUnit.SECONDS);

        SimpleRegistry registry = new SimpleRegistry();
        registry.put(Const.API_STATUS,apiStatus);
        registry.put(Const.RATELIMIT_CHECKER,rateLimitChecker);

        return registry;
    }

    /**
     * Configuration 정보를 기반으로 Camel RouteBuilder를 생성한다.
     *
     * @return camel RouteBuilder
     */
    public RouteBuilder routeBuilder(){
        final FilterFactory ff = FilterFactory.getInstance();

        return RestAPIRouteBuilder.routes(apiEndpoints)
                // add Processor
                .with(ff.blockFilter())
                .with(ff.blackListFilter(blackList))
                .with(ff.rateLimitFilter())
                // add ErrorHandler TODO ErrorHandler로 Factory로 생성?
                .withError(BlockedApiException.class, new UnauthorizedErrorHandler())
                .withError(BlackListIpException.class, new UnauthorizedErrorHandler())
                .withError(ConnectException.class, new ConnectToErrorHandler())
                .withError(Exception.class,new DefaultErrorHandler());
    }
}
