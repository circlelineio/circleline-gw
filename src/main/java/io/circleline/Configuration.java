package io.circleline;

import com.google.common.collect.Lists;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.circleline.message.ApiEndpoint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 1001923 on 16. 1. 14..
 */
@Component
public class Configuration {
    static final String ROUTES_WHITELIST = "routes.whitelist";
    static final String ROUTES_BLACKLIST = "routes.blacklist";
    static final String ROUTES_APIS = "routes.apis";
    static final String LISTEN_PATH = "fromUrl";
    static final String TARGET_URL = "toUrl";
    static final String RATE_LIMIT = "rateLimit";
    static final String APISTATUS_REPOSITORY = "apistatus.repository";

    private final Config conf;

    public static Configuration config(){
        return new Configuration();
    }

    /**
     * 클래스 패스에 있는 nginx.conf 설정파일을 읽어 들이고 싶다면 nginx를 path로 넘긴다.
     *
     * @param path
     * @return
     */
    public static Configuration config(String path){
        return new Configuration(path);
    }

    public Configuration(){
        conf = ConfigFactory.load();
    }
    public Configuration(String path){
        conf = ConfigFactory.load(path);
    }

    public List<String> whiteList(){
        return conf.getStringList(ROUTES_WHITELIST);
    }

    public List<String> blackList(){
        return conf.getStringList(ROUTES_BLACKLIST);
    }

    public long rateLimit(){
        return conf.hasPath(RATE_LIMIT) ? conf.getLong(RATE_LIMIT) : 0;
    }

    public List<ApiEndpoint> apiList(){
        List<ApiEndpoint> apiEndpoints = Lists.newArrayList();
        final List<? extends Config> configList = conf.getConfigList(ROUTES_APIS);
        configList.forEach(config -> {
            apiEndpoints.add(new ApiEndpoint(config.getString(LISTEN_PATH),
                    config.getString(TARGET_URL),
                    config.hasPath(RATE_LIMIT) ? config.getLong(RATE_LIMIT) : rateLimit()));
        });
        return apiEndpoints;
    }

    public String apiStatusRepository(){
        return conf.getString(APISTATUS_REPOSITORY);
    }
}
