package io.circleline.message;

import io.circleline.Configuration;
import io.circleline.RestAPI;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.*;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public class ApiEndpointStatusManagerTest {

    @Test
    public void testBlock(){
        //given
        ApiEndpoint api = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",0l);
        ApiEndpointStatusManager manager =
                new LocalApiEndpointStatusManager(Arrays.asList(api));
        //when
        manager.block(api);
        //than
        assertEquals(true,manager.isBlocked(api));
    }

    @Test
    public void testUnblock(){
        //given
        ApiEndpoint api = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",0l);
        ApiEndpointStatusManager manager =
                new LocalApiEndpointStatusManager(Arrays.asList(api));
        //when
        manager.unblock(api);
        //than
        assertEquals(false,manager.isBlocked(api));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlockStatusException(){
        //given
        ApiEndpoint api = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",0l);
        ApiEndpoint other = new ApiEndpoint("http://2.1.1.1/api1","http://2.2.2.2/api2",0l);

        ApiEndpointStatusManager manager =
                new LocalApiEndpointStatusManager(Arrays.asList(api));
        //when,than
        manager.block(other);
    }

    @Test
    public void testCheckRateLimitAndBlock() throws Exception {
        //given
        ApiEndpoint api = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",2L);
        ApiEndpointStatusManager manager =
                new LocalApiEndpointStatusManager(Arrays.asList(api));

        //when
        manager.incrementTransactionCount(api);
        manager.incrementTransactionCount(api);
        manager.incrementTransactionCount(api);
        manager.checkRateLimitAndBlock();

        //than
        assertEquals(true,manager.isBlocked(api));
    }
}