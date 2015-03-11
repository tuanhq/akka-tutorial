package model.com.fpt.su11.routeAndFaulthandle;

import akka.actor.UntypedActor;

public abstract class Listener extends UntypedActor {

  @Override
  public void onReceive(Object obj) throws Exception {
    summaryResult(obj);
  }

  public abstract void summaryResult(Object obj);

  public abstract boolean checkCondition();

}
