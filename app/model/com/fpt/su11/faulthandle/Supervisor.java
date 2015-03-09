package model.com.fpt.su11.faulthandle;

import scala.concurrent.duration.Duration;
import scala.reflect.internal.Trees.This;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.Terminated;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.stop;
import static akka.actor.SupervisorStrategy.escalate;
import akka.actor.UntypedActor;
import akka.japi.Function;

public class Supervisor extends UntypedActor {
  private ActorRef ref;

  private static SupervisorStrategy strategy = new OneForOneStrategy(10,
      Duration.create("1 minute"), new Function<Throwable, Directive>() {
        @Override
        public Directive apply(Throwable t) {
          if (t instanceof ArithmeticException) {
            return resume();
          } else if (t instanceof NullPointerException) {
            return restart();
          } else if (t instanceof IllegalArgumentException) {
            return stop();
          } else {
            return escalate();
          }
        }
      });

  @Override
  public SupervisorStrategy supervisorStrategy() {
    return strategy;
  }

  public void onReceive(Object o) {
    if (o instanceof Props) {
      ref = getContext().actorOf((Props) o);
      getSender().tell(ref, getSelf());
      getContext().watch(ref);
      
    } else if (o instanceof Terminated){
     System.out.println(this.getClass() + " receive a terminate message from " + getSender()); 
    }else {
      unhandled(o);
    }
  }
}
