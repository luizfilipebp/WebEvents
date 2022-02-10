package com.microservice.util.event;

import com.microservice.requests.Event.EventPostRequestBody;

public class EventPostRequestBodyCreator {
    public static EventPostRequestBody createEventPostRequestBody(){
        return EventPostRequestBody.builder()
                .id(1L)
                .type(EventCreator.createEventToBeSaved().getType())
                .build();
    }
}
