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
        ApiEndpoint aPiEndpoint = new ApiEndpoint("http://0.0.0.0:8080/acme/ping",
                "http://localhost:9000/v1/ping");
        //when
        URLBuilder sut = URLBuilder.build(aPiEndpoint);
        //then
        assertThat(sut).isInstanceOf(URLBuilder.class);
    }

    @Test
    public void httpURLObject() throws Exception {
        //given
        ApiEndpoint aPiEndpoint = new ApiEndpoint("http://0.0.0.0:8080/acme/ping",
                "http://localhost:9000/v1/ping");
        URLBuilder sut = URLBuilder.build(aPiEndpoint);
        //when
        assertThat(sut.fromUrl()).isEqualTo("jetty:http://0.0.0.0:8080/acme/ping");
        assertThat(sut.toUrl()).isEqualTo("http://localhost:9000/v1/ping?bridgeEndpoint=true");
    }
}
