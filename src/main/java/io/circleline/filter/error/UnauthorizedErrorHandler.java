package io.circleline.filter.error;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * BlockFilter를 통해 필터된 요청을 처리함.
 *
 * HTTP 401 Error 응답메시지 전송
 */
public class UnauthorizedErrorHandler implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);

        exchange.getOut().setBody(caused.getMessage());
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 401);
    }
}
