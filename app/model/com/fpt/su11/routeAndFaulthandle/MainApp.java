package model.com.fpt.su11.routeAndFaulthandle;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.WorkApi;
public class MainApp {
 public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("CaculatePISys");
    ActorRef listener = system.actorOf(Props.create(ListenerPi.class, 12),"listenner");
    WorkApi work = new WorkPi();
    ActorRef masterRef = system.actorOf(Props.create(Master.class, 4, listener, work),"master");
    InputPi inputPi;
    for (int i =0 ; i < 12 ; i++) {
      inputPi = new InputPi(i,10000);
      masterRef.tell(inputPi,null);
    }
}
}
