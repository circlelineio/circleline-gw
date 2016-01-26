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
        String fromUrl = "http://0.0.0.0:8080/acme/ping";
        String toUrl = "http://localhost:9000/v1/ping";
        //when
        URLBuilder sut = URLBuilder.build(fromUrl,toUrl);
        //then
        assertThat(sut).isInstanceOf(URLBuilder.class);
    }

    @Test
    public void httpURLObject() throws Exception {
        //given
        String fromUrl = "http://0.0.0.0:8080/acme/ping";
        String toUrl = "http://localhost:9000/v1/ping";
        //when
        URLBuilder sut = URLBuilder.build(fromUrl,toUrl);
        //when
        assertThat(sut.fromUrl()).isEqualTo("jetty:http://0.0.0.0:8080/acme/ping");
        assertThat(sut.toUrl()).isEqualTo("http://localhost:9000/v1/ping?bridgeEndpoint=true");
    }
}
