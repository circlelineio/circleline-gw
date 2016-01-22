package io.circleline.util;

import io.circleline.message.HttpURLObject;
import io.circleline.message.URLObject;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by 1002515 on 2016. 1. 21..
 */
public class ReflectionUtilTest {
    @Test
    public void newInstance() throws Exception {
        //given,when
        URLObject obj = ReflectionUtil.newInstance(HttpURLObject.class, "http://1.1.1.1");
        //then
        assertThat(obj).isInstanceOf(HttpURLObject.class);
    }
}