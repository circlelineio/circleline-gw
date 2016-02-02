package third.hazecast;

import com.hazelcast.cache.ICache;
import io.circleline.Configuration;
import io.circleline.RestAPI;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by 1002515 on 2016. 2. 1..
 */
public class HazelcaseTest {

    @Test
    public void testGet() throws Exception{
        //given
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Key, Value> configuration = new MutableConfiguration();
        Cache<Key, Value> myCache = manager.createCache("myCache", configuration);

        Key k = new Key("1");
        Value v = new Value(k,"v",1L);

        //when
        myCache.put(k,v);
        Value value = myCache.get(k);
        //then
        assertThat(value).isNotNull();
        assertThat(value).isEqualTo(v);
    }

//    public static void main(String[] args) {
//
//        CacheManager manager = Caching.getCachingProvider().getCacheManager();
//        MutableConfiguration<String, String> configuration = new MutableConfiguration<String, String>();
//
//        configuration.setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
//
//        Cache<String, String> myCache = manager.createCache("myCache", configuration);
//
//        myCache.put("key", "value");
//        myCache.get("key");
//
//        //ICache extends Cache interface, provides more functionality
//        ICache<String, String> icache = myCache.unwrap(ICache.class);
//
//        icache.getAsync("key");
//        icache.putAsync("key", "value");
//
//        final ExpiryPolicy customExpiryPolicy = AccessedExpiryPolicy.factoryOf(Duration.TEN_MINUTES).create();
//        icache.put("key", "newValue", customExpiryPolicy);
//
//        //cache size
//        icache.size();
//    }

}
