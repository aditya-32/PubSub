package org.example.processor;

import com.google.inject.assistedinject.Assisted;
import org.example.entity.Topic;
import org.example.entity.TopicSubscriber;
import org.example.subscriber.SubscriberWorker;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    private final Topic topic;
    private final Map<String, SubscriberWorker> subscribeWorkerMap;

    public TopicHandler(@Assisted Topic topic) {
        this.topic = topic;
        this.subscribeWorkerMap  = new HashMap<>();
    }
    public void broadcast() {
        for (var topicSub : topic.getSubscribers() ) {
            startWorker(topicSub);
        }
    }

    private void startWorker(TopicSubscriber topicSub) {
        String subscriberId = topicSub.getSubscriber().getId();
        if (!subscribeWorkerMap.containsKey(subscriberId)) {
            var subscriberWorker =  new SubscriberWorker(topic, topicSub);
            subscribeWorkerMap.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subWorker = subscribeWorkerMap.get(subscriberId);
        subWorker.wakeUp();
    }
}
