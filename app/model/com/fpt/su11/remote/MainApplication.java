package model.com.fpt.su11.remote;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Random;

import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.typesafe.config.ConfigFactory;

public class MainApplication {
  public static void main(String[] args) {
Thread thread1 = new Thread( new Runnable() {
      
      @Override
      public void run() {
        // TODO Auto-generated method stub
        startRemoteCalculatorSystem();
        
      }
    });
    
  thread1.start();
  try {
    Thread.sleep(5000);
  } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  
  Thread thread2 = new Thread( new Runnable() {
    
    @Override
    public void run() {
      // TODO Auto-generated method stub
      startRemoteLookupSystem();
      
    }
  });
  thread2.start();
  }
  
  public static void startRemoteCalculatorSystem() {
    final ActorSystem system = ActorSystem.create("CalculatorSystem",
        ConfigFactory.load().getConfig("calculator"));
        
    system.actorOf(Props.create(CalculatorActor.class), "calculator");
    System.out.println("Started CalculatorSystem");
  }

  public static void startRemoteLookupSystem() {

    final ActorSystem system = ActorSystem.create("LookupSystem",
        ConfigFactory.load().getConfig("remotelookup"));
    final String path = "akka.tcp://CalculatorSystem@127.0.0.1:2552/user/calculator";
    final ActorRef actor = system.actorOf(
        Props.create(LookupActor.class, path), "lookupActor");

    System.out.println("Started LookupSystem");
    final Random r = new Random();
    system.scheduler().schedule(Duration.create(1, SECONDS),
        Duration.create(1, SECONDS), new Runnable() {
          @Override
          public void run() {
            if (r.nextInt(100) % 2 == 0) {
              actor.tell(new Op.Add(r.nextInt(100), r.nextInt(100)), null);
            } else {
              actor.tell(new Op.Subtract(r.nextInt(100), r.nextInt(100)), null);
            }

          }
        }, system.dispatcher());

  }
}
