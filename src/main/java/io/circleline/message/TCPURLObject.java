package io.circleline.message;

/**
 * Created by 1001923 on 16. 1. 21..
 */
public class TCPURLObject implements URLObject {

    private String url;

    public TCPURLObject(String url){
        this.url = url;
    }

    @Override
    public String fromUrl() {
        return null;
    }

    @Override
    public String toUrl() {
        return null;
    }
}
