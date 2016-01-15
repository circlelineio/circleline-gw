package io.circleline;

import io.circleline.message.ApiPath;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by 1001923 on 16. 1. 14..
 */
public class ConfigurationTest {
    @Test
    public void load() throws Exception{
        final Configuration sut = new Configuration();
    }

    @Test
    public void whiteList() throws Exception{
        //given
        final Configuration sut = new Configuration();
        //when
        final List<String> whiteList = sut.whiteList();
        //then
        assertThat(whiteList).isNotEmpty();
    }

    @Test
    public void blackList() throws Exception{
        //given
        final Configuration sut = new Configuration();
        //when
        final List<String> blackList = sut.blackList();
        //then
        assertThat(blackList).isNotEmpty();
    }

    @Test
    public void apiList() throws Exception{
        //given
        final Configuration sut = new Configuration();
        //when
        final List<ApiPath> apiPaths = sut.apiList();
        //then
        assertThat(apiPaths).isNotEmpty();
        final ApiPath apiPath = apiPaths.get(0);
        assertThat(apiPath.getListenPath()).isEqualTo("jetty:http://0.0.0.0:8080/v1/circleline/api1");
        assertThat(apiPath.getTargetUrl()).startsWith("http://localhost:9000/service1/api1");
    }
}
