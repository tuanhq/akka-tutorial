package model.com.fpt.su11.sendmessage;
import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

public class Terminator extends UntypedActor{
  private ActorRef actorref;
  public Terminator(ActorRef ref) {
    // TODO Auto-generated constructor stub
    this.actorref = ref;
    getContext().watch(actorref);
  }
  @Override
  public void onReceive(Object msg) {
    if (msg instanceof Terminated) {
      //if actoref stop then send terminated message
      System.out.println(" Sender send have class:" + getSender().getClass());
      System.out.println(actorref.path() + "has terminated, shutting down system");
      getContext().system().shutdown();
          
      
    }
    
  }
}
