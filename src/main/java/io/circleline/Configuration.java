package io.circleline;

import com.google.common.collect.Lists;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;

/**
 * Created by 1001923 on 16. 1. 14..
 */
public class Configuration {
    static final String ROUTES_WHITELIST = "routes.whitelist";
    static final String ROUTES_BLACKLIST = "routes.blacklist";
    static final String ROUTES_APIS = "routes.apis";

    static final String LISTEN_PATH = "listenPath";
    static final String TARGET_URL = "targetUrl";

    private final Config conf;

    public Configuration(){
        conf = ConfigFactory.load();
    }

    public List<String> whiteList(){
        return conf.getStringList(ROUTES_WHITELIST);
    }

    public List<String> blackList(){
        return conf.getStringList(ROUTES_BLACKLIST);
    }

    public List<ApiPath> apiList(){
        List<ApiPath> apiPaths = Lists.newArrayList();
        final List<? extends Config> configList = conf.getConfigList(ROUTES_APIS);
        configList.forEach(config -> {
            apiPaths.add(new ApiPath(
                    config.getString(LISTEN_PATH),
                    config.getString(TARGET_URL)));
        });
        return apiPaths;
    }
}
