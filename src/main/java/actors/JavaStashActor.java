package actors;

import akka.actor.AbstractActorWithStash;
import akka.japi.pf.ReceiveBuilder;
import messages.Connect;
import messages.Message;
import scala.Option;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by Harmeet Singh(Taara) on 18/8/16.
 *
 * @version 1.0
 */
public class JavaStashActor extends AbstractActorWithStash {

    private boolean online = false;

    @Override
    public PartialFunction<Object, BoxedUnit> receive() {
        return ReceiveBuilder.
                match(Message.class, msg -> {
                    if(online){
                        System.out.println(">>> Message Processing Start");
                        processMessage(msg);
                    }
                    else{
                        System.out.println(">>> Message Stash");
                        stash();
                    }
                }).
                match(Connect.class, x -> {
                    System.out.println(">>> Message UnStash");
                    online = true;
                    unstash();
                }).build();
    }

    /*
    Note that the Stash trait must be mixed into (a subclass of) the Actor trait before any trait/class that overrides the preRestart callback.
    This means it's not possible to write Actor with MyActor with Stash if MyActor overrides preRestart.
     */
    @Override
    public void preRestart(Throwable reason, Option<Object> message) {
        System.out.println("Successfully override preRestart");
    }

    private void processMessage(Message msg) {
        System.out.println("Message : "+msg.getMsg());
    }
}
