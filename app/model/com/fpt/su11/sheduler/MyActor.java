package model.com.fpt.su11.sheduler;

import akka.actor.UntypedActor;

public class MyActor extends UntypedActor{
  int counter =0 ;
  @Override
  public void onReceive(Object msg) throws Exception {
    if ("hello".equals(msg)){
      if (counter == 10 ) {
        getContext().stop(getSelf());
      }else{
      System.out.println("The sender :" + getSender().path() + " has send hello " + counter +"th");
      }
      counter ++;
    }else {
      unhandled(msg);
    }
  }
  
  
}
