package model.com.fpt.su11.sendmessage;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.Props;

public class ApplicationMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MyActorSystem");
        ActorRef pingActor = system.actorOf(Props.create(PingActor.class),"pingActor");
//        ActorRef pingActor = system.actorOf(PingActor.props(), "pingActor");
//        pingActor.tell(new PingActor.Initialize(), null);
       //pingActor.tell(new PingActor.MyClass(-1), null);
        pingActor.tell(new PingActor.MyClass(-1), null);
        // This example app will ping pong 3 times and thereafter terminate the ActorSystem -
        // see counter logic in PingActor
        system.awaitTermination();
    }

}