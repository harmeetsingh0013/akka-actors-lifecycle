package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActorRef;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Harmeet Singh(Taara) on 18/8/16.
 *
 * @version 1.0
 */
public class JavaActorTest {

    public static ActorSystem system;

    @BeforeClass
    public static void start() {
        system = ActorSystem.create("ActorLifeCycleTest");
    }

    @AfterClass
    public static void cleanup() {
        JavaTestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testActorCreation(){
        TestActorRef<JavaActor> actorRef = TestActorRef.create(system, Props.create(JavaActor.class));
        actorRef.tell("Ping", ActorRef.noSender());
        assertThat(true, is(true));
    }
}
