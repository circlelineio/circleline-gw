package io.circleline;

import io.circleline.router.RestAPIRouter;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by 1001923 on 16. 1. 25..
 */
public class RestAPITest {
    @Test
    public void basicRestAPIRouter() throws Exception{
        //given
        final Configuration config = new Configuration("restapi");
        final RestAPI sut = new RestAPI(config);
        //when
        RouteBuilder routeBuilder = sut.routeBuilder();
        //then
        assertThat(routeBuilder).isInstanceOf(RouteBuilder.class);
    }

    @Test
    public void restApi() throws Exception{
        //given
        final Configuration config = new Configuration("restapi");
        //when
        final RestAPI sut = new RestAPI(config);
        //then
        assertThat(sut).isInstanceOf(RestAPI.class);
        assertThat(sut.getApiEndpoints()).isNotEmpty();
        assertThat(sut.getRateLimit()).isEqualTo(0l);
    }
}
