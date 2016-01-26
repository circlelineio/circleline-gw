package io.circleline;

import io.circleline.router.StaticRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

import static io.circleline.Configuration.config;

/**
 * Created by 1001923 on 16. 1. 15..
 */
public class StaticRouterTest {
    @Test
    public void run() throws Exception{
        //given
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(StaticRouteBuilder.routes(config("staticroute").apiList()));
        context.start();
        //when

        //then
//        context.stop();
    }
}
