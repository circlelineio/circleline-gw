package io.circleline.filter.error;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default ErrorHandler
 *
 * 세부 Error 상황이 아니면 모두 이 Handler를 통해 처리됨
 * HTTP 500 Error 응답메시지 전송
 */
public class DefaultErrorHandler implements Processor {

    static Logger LOG = LoggerFactory.getLogger(DefaultErrorHandler.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        exchange.getOut().setBody(caused.getMessage());
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
    }
}
