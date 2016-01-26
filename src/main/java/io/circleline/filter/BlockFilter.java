package io.circleline.filter;

import io.circleline.common.Const;
import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiStatusManager;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public class BlockFilter implements Processor{

    static Logger LOG = LoggerFactory.getLogger(BlockFilter.class);

    private ApiStatusManager apiStatusManager;

    public BlockFilter(ApiStatusManager apiStatusManager){
        this.apiStatusManager = apiStatusManager;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        if(apiStatusManager == null) return;

        ApiEndpoint apiEndpoint = (ApiEndpoint)exchange.getProperty(Const.API_ENDPOINT);

        if(apiStatusManager.isBlocked(apiEndpoint)){
            LOG.info("this transaction is blocked. " + apiEndpoint);
        }
    }
}
