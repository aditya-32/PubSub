package org.example;

import com.google.inject.AbstractModule;
import org.example.processor.Queue;

import static com.google.inject.Scopes.SINGLETON;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        binder().requireAtInjectOnConstructors();
        binder().requireExactBindingAnnotations();
        binder().requireExplicitBindings();
        binder().disableCircularProxies();

        bind(Queue.class).in(SINGLETON);
    }
}
