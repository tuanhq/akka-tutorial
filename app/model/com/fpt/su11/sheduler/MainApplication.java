package model.com.fpt.su11.sheduler;

import java.util.concurrent.TimeUnit;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MainApplication {

  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("MyActorSystem");
    final ActorRef actorRef = system.actorOf(Props.create(MyActor.class),"myActor");
    system.actorOf(Props.create(Terminator.class,actorRef),"myTerminated");
    system.scheduler().schedule(Duration.create(0, TimeUnit.MILLISECONDS), 
        Duration.create(5, TimeUnit.SECONDS), actorRef, "hello",system.dispatcher(), null);
  //  cancellable.cancel();
  //  system.scheduler().scheduleOnce(Duration.create(50, TimeUnit.MILLISECONDS), 
  //  actorRef, "hello", system.dispatcher(), null);


    
  }

}
