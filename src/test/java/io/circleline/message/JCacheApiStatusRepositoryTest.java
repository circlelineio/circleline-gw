package io.circleline.message;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.LongStream;

import static org.fest.assertions.Assertions.assertThat;
/**
 * Created by 1002515 on 2016. 2. 1..
 */
public class JCacheApiStatusRepositoryTest {

    private static ApiEndpoint ae1;
    private static ApiStatusRepository sut;
    private static final Long RATE_LIMIT_COUNT = 5l;

    @BeforeClass
    public static void initRepository(){
        ae1 = new ApiEndpoint("http://1.1.1.1/api1","http://2.2.2.2/api2",RATE_LIMIT_COUNT);
        sut = new JCacheApiStatusRepository(Arrays.asList(ae1));
        sut.initRepository();
    }

    @Test
    public void getApiStatus() throws Exception{
        //given,when
        ApiStatus apiStatus = sut.getApiStatus(ae1);
        //than
        assertThat(apiStatus).isNotNull();
        assertThat(apiStatus.getApiEndpoint().getFrom()).isEqualTo(ae1.getFrom());
    }

    @Test
    public void incrementTransactionCount() throws Exception {
        //given
        ApiStatus apiStatus = sut.getApiStatus(ae1);
        long currentTransactionCount = apiStatus.getTransactionCount();
        //when
        apiStatus.incrementTransactionCount();
        sut.persist(apiStatus);
        //than
        assertThat(sut.getApiStatus(ae1).getTransactionCount()).isEqualTo(currentTransactionCount + 1);
    }

    @Test
    public void isOverRateLimitTrue() throws Exception {
        //given
        ApiStatus apiStatus = sut.getApiStatus(ae1);
        apiStatus.resetTransactionCount();
        sut.persist(apiStatus);
        //when
        LongStream.range(0, RATE_LIMIT_COUNT).forEach(i -> apiStatus.incrementTransactionCount());
        sut.persist(apiStatus);
        //than
        assertThat(sut.getApiStatus(ae1).isOverRateLimit()).isTrue();
    }

    @Test
    public void isOverRateLimitFalse() throws Exception {
        //given
        ApiStatus apiStatus = sut.getApiStatus(ae1);
        apiStatus.resetTransactionCount();
        sut.persist(apiStatus);
        //when
        LongStream.range(0, RATE_LIMIT_COUNT - 1).forEach(i -> apiStatus.incrementTransactionCount());
        sut.persist(apiStatus);
        //than
        assertThat(sut.getApiStatus(ae1).isOverRateLimit()).isFalse();
    }

    @Test
    public void resetTransactionCount() throws Exception {
        //given
        ApiStatus apiStatus = sut.getApiStatus(ae1);
        apiStatus.incrementTransactionCount();
        sut.persist(apiStatus);
        //when
        apiStatus = sut.getApiStatus(ae1);
        apiStatus.resetTransactionCount();
        sut.persist(apiStatus);
        //than
        assertThat(sut.getApiStatus(ae1).getTransactionCount()).isZero();
    }

    @Test
    public void block() throws Exception {
        //given
        ApiStatus apiStatus = sut.getApiStatus(ae1);
        //when
        apiStatus.block();
        sut.persist(apiStatus);
        //than
        assertThat(sut.getApiStatus(ae1).isBlocked()).isTrue();
    }

    @Test
    public void unblock() throws Exception {
        //given
        ApiStatus apiStatus = sut.getApiStatus(ae1);
        //when
        apiStatus.unblock();
        sut.persist(apiStatus);
        //than
        assertThat(sut.getApiStatus(ae1).isBlocked()).isFalse();
    }
}