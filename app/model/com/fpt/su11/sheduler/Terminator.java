package model.com.fpt.su11.sheduler;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

public class Terminator extends UntypedActor{
  private ActorRef actorRef;
  public  Terminator(ActorRef ref) {
    this.actorRef = ref;
    getContext().watch(this.actorRef);
  }
  @Override
  public void onReceive(Object msg) throws Exception {

    if (msg instanceof Terminated) {
      System.out.println("The system start shutdown");
      getContext().system().shutdown();
    }else {
      unhandled(msg);
    }
  }

}
