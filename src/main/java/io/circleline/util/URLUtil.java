package io.circleline.util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 1002515 on 2016. 1. 21..
 */
public class URLUtil {

    public static String getProtocol(String url){
        try {
            URL aURL = new URL(url);
            return aURL.getProtocol();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url");
        }
    }

    private URLUtil(){}
}
