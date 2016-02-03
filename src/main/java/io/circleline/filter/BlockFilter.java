package io.circleline.filter;

import io.circleline.common.Const;
import io.circleline.filter.error.BlockedApiException;
import io.circleline.filter.error.DefaultErrorHandler;
import io.circleline.message.ApiEndpoint;
import io.circleline.message.ApiStatusManager;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * API의 블럭 상태를 확인하고, 블럭된 API에 대한 요청이 수신되면 {@link BlockedApiException}을 발생시킨다.
 *
 * {@link BlockedApiException}에 대한 ErrorHandler가 지정되어 있다면 해당 ErrorHandler에 처리되며,
 * 지정이 되어 있지 않다면 {@link DefaultErrorHandler} 에서 처리된다.
 *
 * 블럭된 API가 아니면 다음 Filter를 처리한다.
 */
public class BlockFilter implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {

        ApiStatusManager apiStatusManager =
                exchange.getContext()
                        .getRegistry()
                        .lookupByNameAndType(Const.API_STATUS_Manager, ApiStatusManager.class);

        if(apiStatusManager == null) return;

        ApiEndpoint apiEndpoint = exchange.getProperty(Const.API_ENDPOINT, ApiEndpoint.class);

        if(apiStatusManager.getApiStatus(apiEndpoint).isBlocked()){
            throw new BlockedApiException("blocked api : " + apiEndpoint.getFrom());
        }
    }
}
