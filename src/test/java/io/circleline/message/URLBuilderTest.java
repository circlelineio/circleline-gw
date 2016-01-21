package io.circleline.message;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by 1001923 on 16. 1. 19..
 */
public class URLBuilderTest {
    @Test
    public void build() throws Exception {
        //given
        ApiPath aPiPath = new ApiPath("http://0.0.0.0:8080/acme/ping",
                "http://localhost:9000/v1/ping");
        //when
        URLBuilder sut = URLBuilder.build(aPiPath,false);
        //then
        assertThat(sut).isInstanceOf(URLBuilder.class);
    }

    @Test
    public void http_bridgeEndpoint_true() throws Exception {
        //given
        ApiPath aPiPath = new ApiPath("http://0.0.0.0:8080/acme/ping",
                "http://localhost:9000/v1/ping");
        URLBuilder sut = URLBuilder.build(aPiPath,true);
        //when
        assertThat(sut.fromUrl()).isEqualTo("http://0.0.0.0:8080/acme/ping");
        assertThat(sut.toUrl()).isEqualTo("http://localhost:9000/v1/ping?bridgeEndpoint=true");
    }

    @Test
    public void http_bridgeEndpoint_false() throws Exception {
        //given
        ApiPath aPiPath = new ApiPath("http://0.0.0.0:8080/acme/ping",
                "http://localhost:9000/v1/ping");
        URLBuilder sut = URLBuilder.build(aPiPath,false);
        //when
        assertThat(sut.fromUrl()).isEqualTo("http://0.0.0.0:8080/acme/ping");
        assertThat(sut.toUrl()).isEqualTo("http://localhost:9000/v1/ping");
    }
}