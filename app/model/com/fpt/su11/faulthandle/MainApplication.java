package model.com.fpt.su11.faulthandle;

import static akka.pattern.Patterns.ask;

import java.util.concurrent.TimeUnit;

import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class MainApplication {
  public static void main(String[] args)  {
    try {
      ActorSystem system;
      Duration timeout = Duration.create(5, TimeUnit.SECONDS);
      system = ActorSystem.create("test");

      Props superprops = Props.create(Supervisor.class);
      ActorRef supervisor = system.actorOf(superprops, "supervisor");
      
      
        ActorRef child = (ActorRef) Await.result(
            ask(supervisor, Props.create(Child.class), 5000), timeout);
        
        system.actorOf(Props.create(Terminated.class,child), "terminated");
        child.tell(42, ActorRef.noSender());
        System.out.println(Await.result(ask(child, "get", 5000), timeout).toString());
        child.tell(new ArithmeticException(), ActorRef.noSender());
        System.out.println(Await.result(ask(child, "get", 5000), timeout).toString());
        child.tell(new NullPointerException(), ActorRef.noSender());
        System.out.println(Await.result(ask(child, "get", 5000), timeout).toString());
        child.tell(new IllegalArgumentException(), ActorRef.noSender());
        
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    
    
  }

}
