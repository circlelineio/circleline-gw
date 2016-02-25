package io.circleline.filter.auth;

import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import java.security.MessageDigest;
import java.util.Base64;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by 1002515 on 2016. 2. 22..
 */
public class BasicAuthTest {

    @Test
    public void challenge() throws Exception {
        //given
        HttpServletRequest reqmock = mock(HttpServletRequest.class);
        String credential = Base64.getEncoder().encodeToString("userid:password".getBytes());
        String scheme = "Basic";
        //when
        when(reqmock.getHeader("Authentication")).thenReturn(scheme + " " + credential);
        Authentication auth = new BasicAuth();
        auth.challenge(reqmock);
        //than
        assertEquals(1,1);
    }


    @Test
    public void base64() throws Exception{

        String value = "ew0KICAiaWQiOiJ0ZXJyeSINCiAgLCJyb2xlIjpbImFkbWluIiwidXNlciJdDQogICwiY29tcGFueSI6InBlcHNpIg0KfQ0K";
        String encoded = new String(Base64.getDecoder().decode(value));
        System.out.println(encoded);
        assertNotNull(encoded);
    }

    @Test
    public void hmac() throws Exception{

        try {
            String secret = "secret";
//            String message = "{\n" +
//                    "  \"id\":\"terry\"\n" +
//                    "  ,\"role\":[\"admin\",\"user\"]\n" +
//                    "  ,\"company\":\"pepsi\"\n" +
//                    "}";

            String message = "ew0KICAiaWQiOiJ0ZXJyeSINCiAgLCJyb2xlIjpbImFkbWluIiwidXNlciJdDQogICwiY29tcGFueSI6InBlcHNpIg0KfQ0K";

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            String hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes()));
            System.out.println(hash);
        } catch (Exception e){
            System.out.println("Error");
        }
    }
}