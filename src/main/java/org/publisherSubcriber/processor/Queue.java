package org.publisherSubcriber.processor;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.publisherSubcriber.entity.Message;
import org.publisherSubcriber.entity.Topic;
import org.publisherSubcriber.entity.TopicSubscriber;
import org.publisherSubcriber.exceptions.PubSubException;
import org.publisherSubcriber.subscriber.ISubscriber;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class Queue {
    private static final Map<String, TopicHandler> handlerMap = new HashMap<>();

    public Topic createTopic(String topicName) throws PubSubException {
        if (handlerMap.containsKey(topicName)) {
            throw new PubSubException("Topic Already Created "+topicName);
        }
        var topic = Topic.builder()
                .name(topicName)
                .build();
        var handler = new TopicHandler(topic);
        handlerMap.put(topic.getName(), handler);
        return topic;
    }

    public void subscribe(Topic topic, ISubscriber subscriber){
        topic.addSubscriber(TopicSubscriber.builder().subscriber(subscriber).build());
    }

    public void sendMessage(Topic topic, Message msg) throws PubSubException{
        if (!handlerMap.containsKey(topic.getName())) {
            throw new PubSubException("Handler not registered for topic "+topic.getName());
        }
        topic.addMessage(msg);
        new Thread(() -> {
            var handler = handlerMap.get(topic.getName());
            handler.broadcast();
        }).start();
    }
}
