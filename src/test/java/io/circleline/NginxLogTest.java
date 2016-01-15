package io.circleline;

import io.circleline.router.StaticRouter;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

import static io.circleline.Configuration.config;

/**
 * Created by 1001923 on 16. 1. 14..
 */
public class NginxLogTest {
    @Test
    public void basic() throws Exception{
        //given
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new StaticRouter(config("nginx")));

        context.start();
    }
}
