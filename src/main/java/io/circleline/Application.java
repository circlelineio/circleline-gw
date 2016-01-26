package io.circleline;

import io.circleline.router.RestAPIRouter;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by 1001923 on 16. 1. 14..
 */
public class Application {
    public static void main(String[] args) throws Exception {
        final Configuration config = new Configuration();
        RestAPIRouter sut = new RestAPI(config).restAPIRouter();
        CamelContext context = new DefaultCamelContext();

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
