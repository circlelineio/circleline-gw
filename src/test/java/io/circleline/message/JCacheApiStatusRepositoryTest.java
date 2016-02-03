package io.circleline.message;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;
/**
 * Created by 1002515 on 2016. 2. 1..
 */
public class JCacheApiStatusRepositoryTest {

    private static ApiEndpoint ae1;
    private static ApiStatusRepository asr;

    @BeforeClass
    public static void initRepository(){
        ae1 = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",5L);
        asr = new JCacheApiStatusRepository(Arrays.asList(ae1));
    }

    @Test
    public void testGetApiStatus() throws Exception{
        //given
        ApiStatus apiStatus = asr.getApiStatus(ae1);
        //when
        //than
        assertThat(apiStatus).isNotNull();
    }

    @Test
    public void testIsOverRateLimit() throws Exception {
        //given
        ApiStatus apiStatus = asr.getApiStatus(ae1);
        //when
        apiStatus.incrementTransactionCount();
        apiStatus.incrementTransactionCount();
        apiStatus.incrementTransactionCount();
        apiStatus.incrementTransactionCount();
        apiStatus.incrementTransactionCount(); // overRateLimit
        asr.persist(apiStatus);

        //than
        assertThat(asr.getApiStatus(ae1).isOverRateLimit()).isEqualTo(true);
    }

    @Test
    public void testIncrementTransactionCount() throws Exception {
        //given
        ApiStatus apiStatus = asr.getApiStatus(ae1);
        long currentTransactionCount = apiStatus.getTransactionCount();
        //when
        apiStatus.incrementTransactionCount();
        asr.persist(apiStatus);
        //than
        assertThat(asr.getApiStatus(ae1).getTransactionCount()).isEqualTo(currentTransactionCount + 1);
    }

    @Test
    public void testResetTransactionCount() throws Exception {
        //given
        ApiStatus apiStatus = asr.getApiStatus(ae1);
        apiStatus.incrementTransactionCount();
        asr.persist(apiStatus);

        //when
        apiStatus = asr.getApiStatus(ae1);
        apiStatus.resetTransactionCount();
        asr.persist(apiStatus);

        //than
        assertThat(asr.getApiStatus(ae1).getTransactionCount()).isEqualTo(0);
    }

    @Test
    public void testBlock() throws Exception {
        //given
        ApiStatus apiStatus = asr.getApiStatus(ae1);

        //when
        apiStatus.block();
        asr.persist(apiStatus);

        //than
        assertThat(asr.getApiStatus(ae1).isBlocked()).isEqualTo(true);
    }

    @Test
    public void testUnblock() throws Exception {
        //given
        ApiStatus apiStatus = asr.getApiStatus(ae1);

        //when
        apiStatus.unblock();
        asr.persist(apiStatus);

        //than
        assertThat(asr.getApiStatus(ae1).isBlocked()).isEqualTo(false);
    }
}