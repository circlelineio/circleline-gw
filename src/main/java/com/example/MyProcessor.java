package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by 1001923 on 16. 1. 5..
 */
public class MyProcessor implements Processor{
    private ActorRef pingActor;

    public MyProcessor(){}

    public MyProcessor(ActorSystem system){
        pingActor = system.actorOf(PingActor.props());
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("We just downloaded:");
        pingActor.tell(new PingActor.Initialize(), null);
    }
}
