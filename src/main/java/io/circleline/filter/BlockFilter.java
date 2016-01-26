package io.circleline.filter;

import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiEndpointStatusManager;
import io.circleline.router.RestAPIRouteBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 1002515 on 2016. 1. 25..
 */
public class BlockFilter implements Processor{

    static Logger LOG = LoggerFactory.getLogger(BlockFilter.class);

    private ApiEndpointStatusManager apiEndpointStatusManager;

    public BlockFilter(ApiEndpointStatusManager apiEndpointStatusManager){
        this.apiEndpointStatusManager = apiEndpointStatusManager;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        if(apiEndpointStatusManager == null) return;

        ApiEndpoint apiEndpoint = (ApiEndpoint)exchange.getProperty(RestAPIRouteBuilder.API_ENDPOT);

        if(apiEndpointStatusManager.isBlocked(apiEndpoint)){
            LOG.info("this transaction is blocked. " + apiEndpoint);
        }
    }
}
