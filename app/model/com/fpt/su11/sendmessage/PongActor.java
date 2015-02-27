package model.com.fpt.su11.sendmessage;
import model.com.fpt.su11.sendmessage.PingActor.MyClass;
import model.com.fpt.su11.sendmessage.PongActor.PongMessage;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PongActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    String[] myData = {"hanoi","saigon","hue","danang","haiphong","vungtau","camau","lamdong","travinh","binhdinh","dalak"}; 
    public static Props props() {
        return Props.create(PongActor.class);
    }

    public static class PongMessage {
        private final String text;

        public PongMessage(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
    
    public void onReceive(Object message) throws Exception {
        if (message instanceof PingActor.PingMessage) {
            PingActor.PingMessage ping = (PingActor.PingMessage) message;
            log.info("In PongActor - received message: {}", ping.getText());
            getSender().tell(new PongMessage("pong"), getSelf());
        } else if (message instanceof PingActor.MyClass){
          MyClass myClass = (MyClass) message;
          System.out.println("Message receive from PingActor have count=" + myClass.getCounter());
          System.out.println("Send to sender value of data of counter");
          String data;
          try {
            data=myData[myClass.getCounter()];
          } catch (Exception ex) {
            data = " Notfound data with value " + myClass.getCounter();
          }
          getSender().tell(new MyClass(myClass.getCounter(), data), getSelf());
        }else {
            unhandled(message);
        }
    }
}