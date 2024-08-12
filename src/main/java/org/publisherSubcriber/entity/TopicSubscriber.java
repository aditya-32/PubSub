package org.publisherSubcriber.entity;

import lombok.Builder;
import lombok.Getter;
import org.publisherSubcriber.subscriber.ISubscriber;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Builder
public class TopicSubscriber {
    @Builder.Default
    private AtomicInteger offset = new AtomicInteger(0);
    private ISubscriber subscriber;
}
