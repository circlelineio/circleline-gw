package io.circleline.filter;

import io.circleline.common.Const;
import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiStatusManager;
import io.circleline.message.LocalApiStatusManager;
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
public class BlockFilterTest extends CamelTestSupport{

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    private ApiEndpoint apiEndpoint = new ApiEndpoint("http://1.1.1.1","http://2.2.2.2",0L);
    private ApiStatusManager apiEndpointStatusManager =
            new LocalApiStatusManager(Arrays.asList(apiEndpoint));

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                BlockFilter filter = new BlockFilter(apiEndpointStatusManager);
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
        apiEndpointStatusManager.block(apiEndpoint);
        //when
        resultEndpoint.expectedMessageCount(0);
        template.sendBody("dummy");
        //than
        resultEndpoint.assertIsSatisfied();
    }
}