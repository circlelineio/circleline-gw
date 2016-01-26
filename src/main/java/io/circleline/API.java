package io.circleline;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Registry;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public interface API {
    public RouteBuilder routeBuilder();
    public Registry getRegistry();
}
