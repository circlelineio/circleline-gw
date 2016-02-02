package io.circleline.message;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public class ApiStatusTest {

    @Test
    public void testBlock(){
        //given
        ApiEndpoint api = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",0l);
        ApiStatusRepository manager =
                new LocalApiStatusRepository(Arrays.asList(api));
        //when
        manager.getApiStatus(api).block();
        //than
        assertEquals(true, manager.getApiStatus(api).isBlocked());
    }

    @Test
    public void testUnblock(){
        //given
        ApiEndpoint api = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",0l);
        ApiStatusRepository manager =
                new LocalApiStatusRepository(Arrays.asList(api));
        //when
        manager.getApiStatus(api).unblock();
        //than
        assertEquals(false, manager.getApiStatus(api).isBlocked());
    }

    @Test(expected = NullPointerException.class)
    public void testBlockStatusException(){
        //given
        ApiEndpoint api = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",0l);
        ApiEndpoint other = new ApiEndpoint("http://2.1.1.1/api1","http://2.2.2.2/api2",0l);

        ApiStatusRepository manager =
                new LocalApiStatusRepository(Arrays.asList(api));
        //when,than
        manager.getApiStatus(other).block();
    }

    @Test
    public void testCheckRateLimitAndBlock() throws Exception {
        //given
        ApiEndpoint api = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",2L);
        ApiStatusRepository manager =
                new LocalApiStatusRepository(Arrays.asList(api));

        ApiStatus status = manager.getApiStatus(api);

        //when
        status.incrementTransactionCount();
        status.incrementTransactionCount();
        status.incrementTransactionCount();
        if(status.isOverRateLimit()){
            status.block();
        }

        //than
        assertEquals(true,status.isBlocked());
    }
}