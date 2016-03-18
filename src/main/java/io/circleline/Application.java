package io.circleline;

import io.circleline.management.keys.KeyManagementFileService;
import io.circleline.router.ManagementAPIRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spi.Registry;

/**
 * Created by 1001923 on 16. 1. 14..
 */
public class Application {
    public static void main(String[] args) throws Exception {
        final Configuration config = new Configuration();
        final RestAPI restAPI = new RestAPI(config);

        CamelContext context = new DefaultCamelContext(initRegisty());
        context.addRoutes(restAPI.routeBuilder());
        context.addRoutes(ManagementAPIRouteBuilder.routes());
        context.start();

//        Main main = new Main();
//        main.enableHangupSupport();
//        main.run();
//        run();

//        ActorSystem system = ActorSystem.create("MyActorSystem");
//        ActorRef pingActor = system.actorOf(PingActor.props(), "pingActor");
//        pingActor.tell(new PingActor.Initialize(), null);
        // This example app will ping pong 3 times and thereafter terminate the ActorSystem -
        // see counter logic in PingActor
//        system.awaitTermination();
//        context.stop();
    }

    private static Registry initRegisty(){
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("keyManagementService",new KeyManagementFileService());

        return registry;
    }

}
