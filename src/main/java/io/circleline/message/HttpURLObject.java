package io.circleline.message;

/**
 * Created by 1001923 on 16. 1. 19..
 */
public class HttpURLObject implements URLObject {
    private String url;
    private boolean bridgeEndpoint;

    public static HttpURLObject build(){
        return new HttpURLObject();
    }

    @Override
    public String fromUrl(){
        return url;
    }

    @Override
    public String toUrl(){
        if(bridgeEndpoint) {
            return url + "?bridgeEndpoint=" + bridgeEndpoint;
        }else{
            return url;
        }
    }

    public HttpURLObject withUrl(String url){
        this.url=url;
        return this;
    }

    public HttpURLObject withBridgeEndpoint(boolean bridgeEndpoint){
        this.bridgeEndpoint = bridgeEndpoint;
        return this;
    }
}
