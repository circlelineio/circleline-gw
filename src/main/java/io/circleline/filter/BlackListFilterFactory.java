package io.circleline.filter;

import io.circleline.Configuration;
import io.circleline.message.ApiPath;
import org.apache.camel.Processor;

/**
 * Created by 1002515 on 2016. 1. 18..
 */
public class BlackListFilterFactory implements FilterFactory {
    private static final BlackListFilterFactory processorFactory = new BlackListFilterFactory();
    private BlackListFilterFactory(){}

    public static FilterFactory getInstance(){
        return processorFactory;
    }

    @Override
    public Processor getFilter(Configuration conf, ApiPath apiPath){
        return new BlackListFilter(conf.blackList());
    }
}
