package io.circleline.filter.error;

import io.circleline.common.Const;
import io.circleline.message.ApiEndpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import java.net.ConnectException;

/**
 * toUrl에 대한 ConnectionException 발생시 에러처리
 * HTTP 500 Error 응답메시지 전송
 */
public class ConnectToErrorHandler implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ConnectException ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, ConnectException.class);
        ApiEndpoint apiEndpoint = exchange.getProperty(Const.API_ENDPOINT,ApiEndpoint.class);

        exchange.getOut().setBody(ex.getMessage() + ". toUrl : " + apiEndpoint.getTo());
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
    }
}
