package com.microservice.util.UserEvents;

import com.microservice.requests.UserEvents.UserEventsPostRequestBody;
import com.microservice.util.event.EventCreator;
import com.microservice.util.user.UserCreator;

import java.time.LocalDateTime;

public class UserEventsPutRequestBodyCreator {
    public static UserEventsPostRequestBody createUserEventsPostRequestBody(){
        return UserEventsPostRequestBody.builder()
                .dateTime(LocalDateTime.parse("2022-02-07T16:15:57.24944"))
                .user(UserCreator.createValidUser())
                .event(EventCreator.createValidEvent())
                .build();
    }
}