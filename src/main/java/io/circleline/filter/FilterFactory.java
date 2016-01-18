package io.circleline.filter;

import io.circleline.Configuration;
import io.circleline.message.ApiPath;
import org.apache.camel.Processor;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public interface FilterFactory {
    public Processor getFilter(Configuration conf, ApiPath apiPath);
}
