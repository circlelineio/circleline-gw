package io.circleline;

import io.circleline.router.RestAPIRouter;
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
        RestAPIRouter restAPIRouter = sut.restAPIRouter();
        //then
        assertThat(restAPIRouter).isInstanceOf(RestAPIRouter.class);
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
