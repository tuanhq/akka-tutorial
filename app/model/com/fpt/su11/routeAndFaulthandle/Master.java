package model.com.fpt.su11.routeAndFaulthandle;

import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.stop;

import java.util.List;
import java.util.ArrayList;

import model.com.fpt.su11.routeAndFaulthandle.DefintionExp.RestartExp;
import model.com.fpt.su11.routeAndFaulthandle.DefintionExp.ResumeExp;
import model.com.fpt.su11.routeAndFaulthandle.DefintionExp.StopExp;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.japi.Function;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import model.com.fpt.su11.routeAndFaulthandle.WorkerAPI.*;
public class Master extends UntypedActor {

  private Router router;
  private ActorRef listener;

  public Master(int numberWorker, ActorRef listener,WorkApi workApi) {
    
    this.listener = listener;
    List<Routee> listRoutee = new ArrayList<Routee>();
    for (int i = 0; i < numberWorker; i++) {
      ActorRef ref = getContext().actorOf(Props.create(Worker.class,workApi, String.valueOf(i)));
      getContext().watch(ref);
      
      listRoutee.add(new ActorRefRoutee(ref));
    }
    router = new Router(new RoundRobinRoutingLogic(), listRoutee);
  }

  public static SupervisorStrategy stratege = new OneForOneStrategy(10,
      Duration.create("1 minutes"), new Function<Throwable, Directive>() {
        @Override
        public Directive apply(Throwable ex) throws Exception {
          if (ex instanceof ResumeExp) {            
            System.out.println("receive exception from my child");
            return resume();

          } else if (ex instanceof RestartExp) {
            return restart();

          } else if (ex instanceof StopExp) {
            return stop();
          } else {
            return escalate();
          }
        };
      });

  @Override
  public SupervisorStrategy supervisorStrategy() {
    return stratege;
  }

  @Override
  public void onReceive(Object message) throws Exception {

    if (message instanceof Terminated) {
      System.out.println("receive message terminated");
      // remove worker stoped
      router = router.removeRoutee(((Terminated) message).actor());
      // create new worker
      ActorRef ref = getContext().actorOf(Props.create(Worker.class));
      getContext().watch(ref);
      router = router.addRoutee(new ActorRefRoutee(ref));
    } else if (message instanceof InputObj) {
      //send work to worker
      router.route(message, getSelf());
    }else if (message instanceof OutputObj) {
      
      listener.tell(message, getSelf());      
    }
    
  }

}
