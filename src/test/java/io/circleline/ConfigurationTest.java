package io.circleline;

import io.circleline.message.ApiEndpoint;
import io.circleline.message.RestAPI;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by 1001923 on 16. 1. 14..
 */
public class ConfigurationTest {

    @Test
    public void restApi() throws Exception{
        //given
        final Configuration sut = new Configuration("restapi");
        //when
        final RestAPI restAPI = sut.restAPI();
        //then
        assertThat(restAPI).isInstanceOf(RestAPI.class);
        assertThat(restAPI.getApiEndpoints()).isNotEmpty();
        assertThat(restAPI.getRateLimit()).isEqualTo(0l);
    }

    @Test
    public void apiEndpoint() throws Exception{
        //given
        final Configuration sut = new Configuration("apiendpoint");
        //when
        final List<ApiEndpoint> apiEndpoints = sut.apiList();
        //then
        assertThat(apiEndpoints).isNotEmpty();
        final ApiEndpoint apiEndpoint = apiEndpoints.get(0);
        assertThat(apiEndpoint.getFromUrl()).isEqualTo("jetty:http://0.0.0.0:8080/v1/acme/ping");
        assertThat(apiEndpoint.getToUrl()).startsWith("http://localhost:9000/v1/ping");
    }

    @Test
    public void blackList() throws Exception{
        //given
        final Configuration sut = new Configuration("blackwhitelist");
        //when
        final List<String> blackList = sut.blackList();
        //then
        assertThat(blackList).isNotEmpty();
    }

    @Test
    public void whiteList() throws Exception{
        //given
        final Configuration sut = new Configuration("blackwhitelist");
        //when
        final List<String> whiteList = sut.whiteList();
        //then
        assertThat(whiteList).isNotEmpty();
    }

    @Test
    public void load() throws Exception{
        new Configuration();
    }
}
