package io.circleline.filter.auth;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by 1002515 on 2016. 2. 22..
 */
public class StandardAuthTest {

    @Test
    public void testChallenge() throws Exception {

        //given
        HttpServletRequest reqMock = mock(HttpServletRequest.class);
        String headerName = "x-api-auth";
        //when
        when(reqMock.getHeader(headerName)).thenReturn("accessToken");
        Authentication auth = new StandardAuth(headerName);
        auth.challenge(reqMock);
        //than
        assertEquals(1,1);

    }
}