package io.circleline.filter;

import org.apache.camel.Exchange;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public abstract class AbstractTrafficMonitor implements TrafficMonitor {

    public void receive(Exchange exchange) {
        HttpServletRequest req = exchange.getIn().getBody(HttpServletRequest.class);
        receive(req.getRequestURI());
    }

    protected abstract void receive(String api);
}
