package io.circleline.filter.auth;

import org.apache.commons.codec.digest.HmacUtils;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by 1002515 on 2016. 2. 25..
 */
public class HmacAuthTest {

    @Test
    public void testChallenge() throws Exception {

        //given
        HttpServletRequest reqmock = mock(HttpServletRequest.class);
        String scheme = "Signature";
        String keyId = "hmac-key-1";
        String algorithm = "hmac-sha1";
        String date = new Date().toString();
        String signedStr = HmacUtils.hmacSha1Hex("secret".getBytes(), ("date:" + date).getBytes());
        String signature = Base64.getEncoder().encodeToString(signedStr.getBytes());
        //when
        when(reqmock.getHeader("Authentication")).thenReturn(
                String.format("keyId=\"%s\",algorithm=\"%s\",signature=\"%s\"",keyId,algorithm,signature));
        when(reqmock.getHeader("date")).thenReturn(date);

        Authentication auth = new HmacAuth();
        auth.challenge(reqmock);
        //than
        assertEquals(1,1);

    }
}