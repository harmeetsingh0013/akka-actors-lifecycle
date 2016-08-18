package actors;

import akka.actor.AbstractActor;
import akka.actor.Status;
import akka.japi.pf.ReceiveBuilder;
import exceptions.EsclateException;
import exceptions.RestartException;
import exceptions.ResumeException;
import exceptions.StopException;
import scala.Option;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by Harmeet Singh(Taara) on 18/8/16.
 *
 * @version 1.0
 */
public class JavaActor extends AbstractActor {

    @Override
    public PartialFunction<Object, BoxedUnit> receive() {
        System.out.println("----   In the receive method "+Thread.currentThread().getName());
        return ReceiveBuilder.
                matchEquals("Ping" , s -> {
                    System.out.println("$$$ Ping Match Successfully");
                    //throw new ResumeException("Might be resume");
                }).
                matchAny(x -> {
                    System.out.println("### Matched value is :  "+ x);
                    sender().tell(new Status.Failure(new Exception("unknown message")), self());
                }).build();
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
        System.out.println(">>> Actor preRestart method calls : "+Thread.currentThread().getName());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println(">>> Actor postRestart method calls : "+Thread.currentThread().getName());
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        System.out.println(">>> Actor preStart method calls "+Thread.currentThread().getName());
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        System.out.println(">>> Actor postStop method calls "+Thread.currentThread().getName());
    }
}
