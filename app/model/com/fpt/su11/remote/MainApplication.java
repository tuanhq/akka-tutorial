package model.com.fpt.su11.remote;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MainApplication {
  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("MyActorSystem");
    final ActorRef createActor = system.actorOf(Props.create(CreationActor.class),"createActor");    
    final Random r = new Random();
    
    //example note using remote
    system.scheduler().schedule(Duration.create(0, TimeUnit.MILLISECONDS), 
        Duration.create(2, TimeUnit.SECONDS),
        new Runnable() {          
          @Override
          public void run() {
            // TODO Auto-generated method stub
             createActor.tell(new Op.Multiply(r.nextInt(10), r.nextInt(20)), null);
          }
        },
        system.dispatcher());    
    system.awaitTermination();
  }
}
