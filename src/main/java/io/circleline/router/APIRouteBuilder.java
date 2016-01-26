package io.circleline.router;

import com.google.common.collect.Lists;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.List;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public abstract class APIRouteBuilder extends RouteBuilder {

    protected final List<Processor> processors = Lists.newArrayList();

    public APIRouteBuilder with(Processor processor){
        processors.add(processor);
        return this;
    }
}
