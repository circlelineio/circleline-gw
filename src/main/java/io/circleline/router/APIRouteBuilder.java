package io.circleline.router;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.camel.Processor;
import org.apache.camel.Service;
import org.apache.camel.builder.RouteBuilder;

import java.util.List;
import java.util.Map;

/**
 * Camel RouteBuilder를 생성한다.
 */
public abstract class APIRouteBuilder extends RouteBuilder {

    protected final List<Processor> processors = Lists.newArrayList();
    protected final Map<Class,Processor> errorHandlers = Maps.newHashMap();
    protected final Map<String,Service> services = Maps.newHashMap();

    public APIRouteBuilder with(Processor processor){
        processors.add(processor);
        return this;
    }

    /**
     * Exception 하위 class별로 errorHandler를 설정한다.
     * Processor 처리 중 Exception이 발생하면 ErrorHandler가 호출되어 관련 비즈니스 로직을 처리하기 된다.
     *
     * @param clazz
     * @param errorHandler
     * @return
     */
    public APIRouteBuilder withError(Class clazz, Processor errorHandler){
        errorHandlers.put(clazz, errorHandler);
        return this;
    }

    @Override
    public void configure() throws Exception {
        initErrorHandler();
        initRoute();
    }

    protected abstract void initErrorHandler();
    protected abstract void initRoute();

}
