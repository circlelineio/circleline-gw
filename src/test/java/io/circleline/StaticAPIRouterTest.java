package io.circleline;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

/**
 * Created by 1001923 on 16. 1. 25..
 */
public class StaticAPIRouterTest {
    @Test
    public void restAPIRouter() throws Exception {
        //given
        final Configuration config = new Configuration("restapi");
        RouteBuilder router = new StaticAPI(config).routeBuilder();
        CamelContext context = new DefaultCamelContext();
        //when
        context.addRoutes(router);
        context.start();

        //then
        context.stop();
    }
}
