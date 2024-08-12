package org.example.entity;

import lombok.Builder;
import lombok.Getter;
import org.example.subscriber.ISubscriber;

import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Builder
public class TopicSubscriber {
    @Builder.Default
    private AtomicInteger offset = new AtomicInteger(0);
    private ISubscriber subscriber;
}
