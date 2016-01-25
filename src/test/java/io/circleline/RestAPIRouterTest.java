package io.circleline;

import io.circleline.router.RestAPIRouter;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

/**
 * Created by 1001923 on 16. 1. 25..
 */
public class RestAPIRouterTest {
    @Test
    public void restAPIRouter() throws Exception{
        //given
        final Configuration config = new Configuration("restapi");
        RestAPIRouter sut = new RestAPI(config).restAPIRouter();
        CamelContext context = new DefaultCamelContext();
        //when
        context.addRoutes(sut);
        context.start();



        //then
        context.stop();
    }
}
