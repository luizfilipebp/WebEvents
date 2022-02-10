package com.microservice.util.event;

import com.microservice.requests.Event.EventPutRequestBody;

public class EventPutRequestBodyCreator {
    public static EventPutRequestBody createEventPutRequestBody(){
        return EventPutRequestBody.builder()
                .id(EventCreator.createValidUpdatedEvent().getId())
                .type(EventCreator.createValidUpdatedEvent().getType())
                .build();
    }
}
