package io.circleline;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by 1001923 on 16. 1. 25..
 */
public class RestAPIRouteBuilderTest {
    @Test
    public void restAPIRouter() throws Exception{
        //given
        final Configuration config = new Configuration("restapi");
        final RestAPI restAPI = new RestAPI(config);
        //when
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(restAPI.routeBuilder());
        context.start();

        final ProducerTemplate producerTemplate = context.createProducerTemplate();
        final String result = producerTemplate
                .requestBody("jetty:http://0.0.0.0:8080/acme/ping", null,
                        String.class);
        //then
        assertThat(result).contains("pong");
        context.stop();
    }
}
