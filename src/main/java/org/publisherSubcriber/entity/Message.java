package org.publisherSubcriber.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Message {
    private String msg;
}
