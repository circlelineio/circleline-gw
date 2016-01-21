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
        StringBuilder sb = new StringBuilder();
        sb.append(withProtocol());
        sb.append(withWildCard(url));
        return sb.toString();
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

    @Override
    public String toUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append(withBridgeEndpoint());
        return sb.toString();
    }

    private String withBridgeEndpoint(){
        return "?bridgeEndpoint=true";
    }

}
