package io.circleline.message;

import com.google.common.net.HostAndPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 1001923 on 16. 1. 21..
 */
public class URLBuilder {
    static Logger LOG = LoggerFactory.getLogger(HttpURLObject.class);

    private ApiPath apiPath;
    private URLObject fromUrlObject;
    private URLObject toUrlObject;

    /**
     * TODO 프로토콜을 분석해서 그에 맞는 URLObject를 만들어줘야 한다. 현재는 HTTP로 고정했음.
     *
     * @param aPiPath
     * @param bridgeEndpoint
     * @return
     */
    public static URLBuilder build(ApiPath aPiPath, boolean bridgeEndpoint){
        final URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.apiPath = aPiPath;
        urlBuilder.fromUrlObject = new HttpURLObject().withUrl(aPiPath.getListenPath());
        urlBuilder.toUrlObject = new HttpURLObject().withUrl(aPiPath.getTargetUrl()).withBridgeEndpoint(bridgeEndpoint);
        return urlBuilder;
    }

    public String fromUrl(){
        return fromUrlObject.fromUrl();
    }

    public String toUrl(){
        return toUrlObject.toUrl();
    }

    public void analyze(){
        final String listenPath = apiPath.getListenPath();
        final HostAndPort hostAndPort = HostAndPort.fromString(listenPath);
        final String hostText = hostAndPort.getHostText();
        final int port = hostAndPort.getPort();

        LOG.debug(hostText);
        LOG.debug(""+port);

    }


    //        final String endpoint = fromNullable(getenv(EBOOKAPI_HOST)).or(defaultEndPoint());
//        final String stripped = endpoint.replaceAll(".*://", "");
//        final HostAndPort hostAndPort = HostAndPort.fromString(stripped);
//        final String hostText = hostAndPort.getHostText();
//        final int port = hostAndPort.getPortOrDefault(DEFAULT_PORT);
//        final String address = isNullOrEmpty(hostText) ? DEFAULT_HOST : hostText;
}
