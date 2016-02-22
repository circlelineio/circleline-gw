package io.circleline.filter.auth;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.servlet.http.HttpServletRequest;

/**
 * Authentication Filter
 */
public class AuthFilter implements Processor{

    private Authentication auth;

    public void AuthFilter(Authentication auth){
        this.auth = auth;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        HttpServletRequest req = exchange.getIn().getBody(HttpServletRequest.class);
        if(req != null){
            auth.challenge(req);
        }
    }
}
