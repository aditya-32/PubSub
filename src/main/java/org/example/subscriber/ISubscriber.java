package org.example.subscriber;

import org.example.entity.Message;

public interface ISubscriber {
    String getId();
    void consumer(Message message);
}
