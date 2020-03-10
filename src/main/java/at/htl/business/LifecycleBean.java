package at.htl.business;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class LifecycleBean {

    void onStart(@Observes StartupEvent event) {

    }

    void onStop(@Observes ShutdownEvent event) {

    }
}
