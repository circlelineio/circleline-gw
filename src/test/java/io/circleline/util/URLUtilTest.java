package io.circleline.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 1002515 on 2016. 1. 21..
 */
public class URLUtilTest {

    @Test
    public void testGetProtocol() throws Exception {
        String protocol = URLUtil.getProtocol("http://localhost");
        assertEquals(protocol,"http");
    }
}