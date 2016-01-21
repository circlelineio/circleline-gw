package io.circleline.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 1001923 on 16. 1. 21..
 */
public class URLBuilder {
    static Logger LOG = LoggerFactory.getLogger(HttpURLObject.class);
    private static final String HTTP = "http";

    private ApiPath apiPath;
    private URLObject fromUrlObject;
    private URLObject toUrlObject;

    /**
     *
     * @param apiPath
     * @param bridgeEndpoint
     * @return
     */
    public static URLBuilder build(ApiPath apiPath, boolean bridgeEndpoint){
        final URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.apiPath = apiPath;
        urlBuilder.fromUrlObject = urlBuilder.fromUrlObject();
        urlBuilder.toUrlObject = urlBuilder.toUrlObject(bridgeEndpoint);
        return urlBuilder;
    }

    public String fromUrl(){
        return fromUrlObject.fromUrl();
    }
    public String toUrl(){
        return toUrlObject.toUrl();
    }

    private URLObject fromUrlObject(){
        try {
            URL aURL = new URL(apiPath.getListenPath());
            LOG.debug(aURL.getProtocol());
            switch (aURL.getProtocol()){
                case HTTP:
                    return new HttpURLObject().withUrl(apiPath.getListenPath());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new TCPURLObject();
    }
    private URLObject toUrlObject(boolean bridgeEndpoint){
        try {
            URL aURL = new URL(apiPath.getListenPath());
            LOG.debug(aURL.getProtocol());
            switch (aURL.getProtocol()){
                case HTTP:
                    return new HttpURLObject().withUrl(apiPath.getTargetUrl()).withBridgeEndpoint(bridgeEndpoint);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new TCPURLObject();
    }
}
