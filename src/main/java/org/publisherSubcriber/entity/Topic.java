package org.publisherSubcriber.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Topic {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    @Builder.Default
    private List<Message> messages = new ArrayList<>();
    @Builder.Default
    private List<TopicSubscriber> subscribers = new ArrayList<>();

    public void addSubscriber(TopicSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
