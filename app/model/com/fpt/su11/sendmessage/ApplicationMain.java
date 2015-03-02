package model.com.fpt.su11.sendmessage;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.Props;

public class ApplicationMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MyActorSystem");
        ActorRef pingActor = system.actorOf(Props.create(PingActor.class),"pingActor");
        pingActor.tell(new PingActor.MyClass(-1), null);        
        system.awaitTermination();
    }

}