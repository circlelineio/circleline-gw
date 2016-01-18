package io.circleline.filter.ratelimit;

import io.circleline.filter.TrafficMonitor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class RateLimitFilter implements Processor {

    private TrafficMonitor trafficMonitor;

    public RateLimitFilter(TrafficMonitor trafficMonitor){
        this.trafficMonitor = trafficMonitor;
    }

    /**
     *
     * @param exchange
     * @throws Exception
     */
    public void process(Exchange exchange) throws Exception {
        trafficMonitor.receive(exchange);
    }
}

