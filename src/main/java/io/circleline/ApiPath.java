package io.circleline;

import lombok.Data;

/**
 * 하나의 API Gateway 경로 정보 저장
 */
@Data
public class ApiPath{
    private String listenPath;
    private String targetUrl;
    private boolean bridgeEndpoint;

    public ApiPath(String from, String to) {
        this(from,to,true);
    }

    public ApiPath(String from, String to, boolean bridgeEndpoint) {
        this.listenPath = from;
        this.targetUrl = to;
        this.bridgeEndpoint = bridgeEndpoint;
    }

//    public String getTargetUrl() {
//        return targetUrl + "?bridgeEndpoint=" + bridgeEndpoint;
//    }
}