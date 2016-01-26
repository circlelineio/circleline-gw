package io.circleline;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
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
        RouteBuilder router = new RestAPI(config).routeBuilder();
        CamelContext context = new DefaultCamelContext();
        //when
        context.addRoutes(router);
        context.start();

        final ProducerTemplate producerTemplate = context.createProducerTemplate();
        final String result = producerTemplate.requestBody("jetty:http://0.0.0.0:8080/acme/ping", null,
                String.class);

//        context.stop();
    }
}
