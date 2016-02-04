package io.circleline.filter;

import io.circleline.common.Const;
import io.circleline.common.StatusRepositoryType;
import io.circleline.filter.error.BlockedApiException;
import io.circleline.filter.error.UnauthorizedErrorHandler;
import io.circleline.message.*;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public class BlockFilterTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    private ApiEndpoint apiEndpoint = new ApiEndpoint("http://1.1.1.1", "http://2.2.2.2", 0L);
    private ApiStatusManager apiStatusManager =
            new ApiStatusManager(Arrays.asList(apiEndpoint), StatusRepositoryType.LOCAL);

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {

                onException(BlockedApiException.class)
                        .handled(true)
                        .process(new UnauthorizedErrorHandler())
                        .stop();

                BlockFilter filter = new BlockFilter(apiStatusManager);
                from("direct:start")
                        .setProperty(Const.API_ENDPOINT)
                        .constant(apiEndpoint)
                        .process(filter)
                        .to("mock:result");
            }
        };
    }

    @Test
    public void testBlockFilter() throws Exception {
        //given
        ApiStatus apiStatus = apiStatusManager.getApiStatus(apiEndpoint);
        apiStatus.block();
        apiStatusManager.persist(apiStatus);
        resultEndpoint.expectedMessageCount(0);
        //when
        template.requestBody("dummy");
        //than
        resultEndpoint.assertIsSatisfied();
    }
}