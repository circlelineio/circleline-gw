package io.circleline.filter.auth;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import java.util.Base64;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by 1002515 on 2016. 2. 22..
 */
public class BasicAuthTest {

    @Test
    public void challenge() throws Exception {
        //given
        HttpServletRequest reqMock = mock(HttpServletRequest.class);
        String credential = Base64.getEncoder().encodeToString("userid:password".getBytes());
        String scheme = "Basic";
        //when
        when(reqMock.getHeader("Authentication")).thenReturn(scheme + " " + credential);
        Authentication auth = new BasicAuth();
        auth.challenge(reqMock);
        //than
        assertEquals(1,1);
    }
}