package io.circleline.message;

/**
 * Created by 1001923 on 16. 1. 19..
 */
public class HttpURLObject implements URLObject {
    private String url;

    public HttpURLObject(String url){
        this.url = url;
    }

    @Override
    public String fromUrl() {
        return new StringBuilder()
                .append(withProtocol())
                .append(withWildCard(url))
                .toString();
    }

    @Override
    public String toUrl() {
        return new StringBuilder()
                .append(url)
                .append(withBridgeEndpoint())
                .toString();
    }

    private String withProtocol(){
        return "jetty:";
    }

    private String withWildCard(String fromUrl){
        if(fromUrl.endsWith("/*")){
            return fromUrl.replace("/*","?matchOnUriPrefix=true");
        }
        return fromUrl;
    }

    private String withBridgeEndpoint(){
        return "?bridgeEndpoint=true";
    }

}
