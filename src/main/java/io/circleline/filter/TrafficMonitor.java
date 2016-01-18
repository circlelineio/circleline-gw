package io.circleline.filter;

import org.apache.camel.Exchange;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public interface TrafficMonitor {
    /**
     *
     * @param exchange
     */
    public void receive(Exchange exchange);
}
