import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import model.com.fpt.su11.remote.MainApplication;
import model.com.fpt.su11.sendmessage.PingActor;
import model.com.fpt.su11.sheduler.MyActor;
import model.com.fpt.su11.sheduler.Terminator;

import org.junit.Before;
import org.junit.Test;

import play.twirl.api.Content;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {
  ActorSystem system;  
  @Before
  public void setup() throws IOException {
    system = ActorSystem.create("MyActorSystem");
  }

    //@Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

   // @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }
   // @Test
    public void testSendMessageBetweenActor(){
     // ActorSystem system = ActorSystem.create("MyActorSystem");
      ActorRef pingActor = system.actorOf(Props.create(PingActor.class),"pingActor");
//      ActorRef pingActor = system.actorOf(PingActor.props(), "pingActor");
//      pingActor.tell(new PingActor.Initialize(), null);
     //pingActor.tell(new PingActor.MyClass(-1), null);
       pingActor.tell(new PingActor.MyClass(-1), null);
      // This example app will ping pong 3 times and thereafter terminate the ActorSystem -
      // see counter logic in PingActor
      system.awaitTermination();
    }
   // @Test
    public void testSchedudule() {
    //ActorSystem system = ActorSystem.create("MyActorSystem");
    final ActorRef actorRef = system.actorOf(Props.create(MyActor.class),"myActor");
    system.actorOf(Props.create(Terminator.class,actorRef),"myTerminated");
    system.scheduler().schedule(Duration.create(0, TimeUnit.MILLISECONDS), 
        Duration.create(5, TimeUnit.SECONDS), actorRef, "hello",system.dispatcher(), null);
  //  cancellable.cancel();
  //  system.scheduler().scheduleOnce(Duration.create(50, TimeUnit.MILLISECONDS), 
  //  actorRef, "hello", system.dispatcher(), null);

    }
    @Test
    public void testRemote() {
      System.out.println("Start test remote");
      Thread thread1 = new Thread( new Runnable() {
        
        @Override
        public void run() {
          // TODO Auto-generated method stub
          System.out.println("Start remote on 2552");
          MainApplication.startRemoteCalculatorSystem();
          
        }
      });
      
    thread1.start();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    Thread thread2 = new Thread( new Runnable() {
      
      @Override
      public void run() {
        // TODO Auto-generated method stub
        
        System.out.println("Start remote loopkup at 2553");
        MainApplication.startRemoteLookupSystem();
        
      }
    });
    thread2.start();
    
    
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println("Please enter any key to exit :");
    String username = null;
    try {
        username = reader.readLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("You entered : " + username);
    
    
    }
    
    
  
}
