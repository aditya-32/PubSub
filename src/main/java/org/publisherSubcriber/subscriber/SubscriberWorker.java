package org.publisherSubcriber.subscriber;

import com.google.inject.assistedinject.Assisted;
import lombok.SneakyThrows;
import org.publisherSubcriber.entity.Topic;
import org.publisherSubcriber.entity.TopicSubscriber;

public class SubscriberWorker implements Runnable{
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(@Assisted Topic topic, @Assisted TopicSubscriber topicSubsriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubsriber;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (topicSubscriber) {
            do {
                var currOffset = topicSubscriber.getOffset().get();
                var messages = topic.getMessages();
                while (currOffset >= messages.size()) {
                    topicSubscriber.wait();
                }
                var subscriber = topicSubscriber.getSubscriber();
                subscriber.consumer(messages.get(currOffset));
                topicSubscriber.getOffset().compareAndSet(currOffset, currOffset+1);
            } while (true);
        }
    }

    synchronized public void wakeUp() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
