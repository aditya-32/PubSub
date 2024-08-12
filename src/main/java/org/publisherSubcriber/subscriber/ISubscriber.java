package org.publisherSubcriber.subscriber;

import org.publisherSubcriber.entity.Message;

public interface ISubscriber {
    String getId();
    void consumer(Message message);
}
