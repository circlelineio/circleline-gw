package com.example;

import akka.actor.ActorSystem;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

public class ApplicationMain {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("MyActorSystem");
        CamelContext context = new DefaultCamelContext();

        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("test-jms:queue:test.queue")
                .process(new MyProcessor(system))
                        .to("file://test");
            }
        });

        ProducerTemplate template = context.createProducerTemplate();

        context.start();

        for (int i = 0; i < 10; i++) {
            template.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
        }

//        ActorRef pingActor = system.actorOf(PingActor.props(), "pingActor");
//        pingActor.tell(new PingActor.Initialize(), null);
        // This example app will ping pong 3 times and thereafter terminate the ActorSystem -
        // see counter logic in PingActor
        system.awaitTermination();
//        context.stop();
    }

}