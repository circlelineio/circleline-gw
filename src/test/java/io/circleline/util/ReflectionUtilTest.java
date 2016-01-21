package io.circleline.util;

import io.circleline.message.HttpURLObject;
import io.circleline.message.URLObject;
import junit.framework.TestCase;

/**
 * Created by 1002515 on 2016. 1. 21..
 */
public class ReflectionUtilTest extends TestCase {

    public void testNewInstance() throws Exception {
        URLObject obj = ReflectionUtil.newInstance(HttpURLObject.class, "http://1.1.1.1");
        assertNotNull(obj);
    }
}