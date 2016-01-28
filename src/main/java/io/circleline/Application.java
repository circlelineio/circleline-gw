package io.circleline;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.rmi.registry.Registry;

/**
 * Created by 1001923 on 16. 1. 14..
 */
public class Application {
    public static void main(String[] args) throws Exception {
        final Configuration config = new Configuration();
        final RestAPI api = new RestAPI(config);
        final RouteBuilder sut = api.routeBuilder();
        CamelContext context = new DefaultCamelContext(api.getRegistry());

        context.addRoutes(sut);
        context.start();

//        Main main = new Main();
//        main.enableHangupSupport();
//        main.run();
//        run();
    }

//    public static void run() throws Exception{
//        ActorSystem system = ActorSystem.create("MyActorSystem");
//        CamelContext context = new DefaultCamelContext();
//
//        context.start();



//        ActorRef pingActor = system.actorOf(PingActor.props(), "pingActor");
//        pingActor.tell(new PingActor.Initialize(), null);
        // This example app will ping pong 3 times and thereafter terminate the ActorSystem -
        // see counter logic in PingActor
//        system.awaitTermination();
//        context.stop();
//    }
}
