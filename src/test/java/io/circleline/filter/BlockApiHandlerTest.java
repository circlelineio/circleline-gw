package io.circleline.filter;

import io.circleline.filter.error.BlockedApiException;
import io.circleline.filter.error.UnauthorizedErrorHandler;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by 1002515 on 2016. 1. 27..
 */
public class BlockApiHandlerTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                onException(BlockedApiException.class)
                        .handled(true)
                        .process(new UnauthorizedErrorHandler()).stop();

                from("direct:start")
                        .process(exchange -> {throw new BlockedApiException("http://1.1.1.1/v1/blockedapi");})
                        .process(exchange -> {
                            System.out.println("can't be arrived");
                        })
                        .to("mock:result");
            }
        };
    }

    @Test
    public void testBlockFilter() throws Exception {
        //given
        resultEndpoint.expectedMessageCount(0);
        //when
//        template.sendBody("dummy");
        Object result = template.requestBody("dummy");
        System.out.println(result.getClass() + "::" + result);
        //than
        resultEndpoint.assertIsSatisfied();
        assertNotNull(result);
    }
}