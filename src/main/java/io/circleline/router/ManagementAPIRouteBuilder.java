package io.circleline.router;

import io.circleline.domain.KeyInfo;
import io.circleline.domain.KeyStatus;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * Created by 1002515 on 2016. 3. 18..
 */
public class ManagementAPIRouteBuilder extends RouteBuilder{

    private ManagementAPIRouteBuilder(){}

    public static ManagementAPIRouteBuilder routes(){
        return new ManagementAPIRouteBuilder();
    }

    @Override
    public void configure() throws Exception {

        rest("/keys/").description("Key management service")
                .consumes("application/json")
                .produces("application/json")

                .post("/create")
                    .description("Create Keys")
                    .type(KeyInfo.class)
                    .outType(KeyStatus.class)
                    .to("bean:keyManagementService?method=createKey");

//        restConfiguration().component("jetty")
//                .bindingMode(RestBindingMode.json)
//                .dataFormatProperty("prettyPrint", "true")
//                .port(8000);
    }
}
