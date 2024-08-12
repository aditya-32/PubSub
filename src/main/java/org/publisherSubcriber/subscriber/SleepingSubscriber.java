package org.publisherSubcriber.subscriber;

import com.google.inject.assistedinject.Assisted;
import org.publisherSubcriber.entity.Message;

public class SleepingSubscriber implements ISubscriber{
    private final String id;

    public SleepingSubscriber (@Assisted String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void consumer(Message message) {
        System.out.println("Message Received "+message.getMsg()+" by subscriber "+id);
        try{
            Thread.sleep(5000);
        } catch (Exception ignored) {

        }
    }
}
