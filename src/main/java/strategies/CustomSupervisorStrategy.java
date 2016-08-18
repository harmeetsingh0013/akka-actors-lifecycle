package strategies;

import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategyConfigurator;
import akka.japi.pf.DeciderBuilder;
import exceptions.EsclateException;
import exceptions.RestartException;
import exceptions.ResumeException;
import exceptions.StopException;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by Harmeet Singh(Taara) on 18/8/16.
 *
 * @version 1.0
 */
public class CustomSupervisorStrategy implements SupervisorStrategyConfigurator {

    @Override
    public SupervisorStrategy create() {
        System.out.println("**** SupervisorStrategy Override Successfully ****");
        return new OneForOneStrategy(5, Duration.create(1, TimeUnit.MINUTES),
                DeciderBuilder.match(ResumeException.class, e -> SupervisorStrategy.resume())
                        .match(RestartException.class, e -> SupervisorStrategy.restart())
                        .match(StopException.class, e -> SupervisorStrategy.stop())
                        .match(EsclateException.class, e -> SupervisorStrategy.escalate())
                        .matchAny(e -> SupervisorStrategy.escalate()).build());
    }
}
