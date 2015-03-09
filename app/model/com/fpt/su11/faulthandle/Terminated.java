package model.com.fpt.su11.faulthandle;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class Terminated extends UntypedActor{
  private ActorRef ref;
  public Terminated(ActorRef ref) {
    
    this.ref = ref;
    getContext().watch(this.ref);
  }
  @Override
  public void onReceive(Object arg0) throws Exception {
    // TODO Auto-generated method stub
    if (arg0 instanceof akka.actor.Terminated ){
      System.out.println("the sender :" + getSender() + " have sent a terminate message");
    }
  }

}
