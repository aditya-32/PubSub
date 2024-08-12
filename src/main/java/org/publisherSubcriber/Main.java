package org.publisherSubcriber;

import com.google.inject.Guice;
import org.publisherSubcriber.entity.Message;
import org.publisherSubcriber.processor.Queue;
import org.publisherSubcriber.subscriber.SleepingSubscriber;

public class Main {
    public static void main(String[] args) {
        var injector = Guice.createInjector(new ApplicationModule());
        var queue = injector.getInstance(Queue.class);
        var topic = queue.createTopic("1");
        var s1 = new SleepingSubscriber("s1");
        var s2 = new SleepingSubscriber("s2");
        queue.subscribe(topic, s1);
        queue.subscribe(topic, s2);
        var msg = Message.builder().msg("Hey Aditya").build();
        queue.sendMessage(topic, msg);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        msg = Message.builder().msg("Hello there, Aditya").build();
        queue.sendMessage(topic, msg);
    }
}