package com.microservice.util.event;

import com.microservice.models.Event;

public class EventCreator {
    public static Event createEventToBeSaved(){
        return Event.builder()
                .id(1L)
                .type("one")
                .build();

    }

    public static Event createValidEvent(){
        return Event.builder()
                .id(2L)
                .type("two")
                .build();
    }

    public static Event createValidUpdatedEvent(){
        return Event.builder()
                .id(3L)
                .type("tree")
                .build();
    }
}
