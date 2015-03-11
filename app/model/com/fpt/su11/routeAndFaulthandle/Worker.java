package model.com.fpt.su11.routeAndFaulthandle;

import akka.actor.UntypedActor;

import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.WorkApi;
import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.OutputObj;
import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.InputObj;

public class Worker extends UntypedActor{
  private WorkApi work;
  private String name;
  public Worker(WorkApi work, String name) {
   this.work = work;
   this.name = name;
  }

  @Override
  public void onReceive(Object message) throws Exception {
    if (message instanceof InputObj) {      
       System.out.println("WOKER "+ name +"receive start:" + ((InputPi)message).getStart() + " number element:" + ((InputPi)message).getNumberElement());
       OutputObj ouputObj = work.executeWork((InputObj) message);
       getSender().tell(ouputObj, getSelf());
    }
    
  }

}
