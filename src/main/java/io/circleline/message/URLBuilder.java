package io.circleline.message;

import io.circleline.util.ReflectionUtil;
import io.circleline.util.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1001923 on 16. 1. 21..
 */
public class URLBuilder {
    static Logger LOG = LoggerFactory.getLogger(HttpURLObject.class);
    private static final String HTTP = "http";
    private static final String TCP = "tcp";

    private URLObject fromUrlObject;
    private URLObject toUrlObject;

    private static final Map<String,Class<?>> URL_OBJECT_MAP =
            Collections.unmodifiableMap(new HashMap(){
                {
                    put(HTTP,HttpURLObject.class);
                    put(TCP,TCPURLObject.class);
                }
            });

    private static URLObject getURLObject(String url){
        String protocol = URLUtil.getProtocol(url);
        if(!URL_OBJECT_MAP.containsKey(protocol)){
            throw new IllegalArgumentException("invalid protocol");
        }
        return ReflectionUtil.newInstance(URL_OBJECT_MAP.get(protocol),url);
    }

    /**
     *
     * @param fromUrl
     * @param toUrl
     * @return
     */
    public static URLBuilder build(String fromUrl, String toUrl){
        final URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.fromUrlObject = getURLObject(fromUrl);
        urlBuilder.toUrlObject = getURLObject(toUrl);
        return urlBuilder;
    }

    public String fromUrl(){
        return fromUrlObject.fromUrl();
    }

    public String toUrl(){
        return toUrlObject.toUrl();
    }

    private URLBuilder(){}
}
