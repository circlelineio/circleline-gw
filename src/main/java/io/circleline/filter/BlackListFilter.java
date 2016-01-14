package io.circleline.filter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Blacklist Filter
 */
public class BlackListFilter implements Processor {

    static Logger LOG = LoggerFactory.getLogger(BlackListFilter.class);

    /**
     * BlackList
     */
    private List<String> blackList = Collections.emptyList();

    public BlackListFilter(List<String> blackList){
        this.blackList = blackList;
    }

    public void setBlackList(List<String> blackList){
        this.blackList = blackList;
    }

    /**
     * Blacklist를 관리하며, 요청 IP가 blacklist에 있으면 에러 응답을 전송한다.
     *
     * @param exchange
     * @throws Exception
     */
    public void process(Exchange exchange) throws Exception {
        if(blackList.isEmpty()) return;
        HttpServletRequest req = exchange.getIn().getBody(HttpServletRequest.class);
        if(req != null){
            String remoteAddr = req.getRemoteAddr();
//            int remotePort = req.getRemotePort();
            if(blackList.contains(remoteAddr)){
                LOG.info("This address for request in blacklist. {}",remoteAddr);
            }
        }
    }
}
