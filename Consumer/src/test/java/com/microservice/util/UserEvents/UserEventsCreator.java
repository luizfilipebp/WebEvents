package com.microservice.util.UserEvents;

import com.microservice.models.UserEvents;
import com.microservice.util.event.EventCreator;
import com.microservice.util.user.UserCreator;

import java.time.LocalDateTime;

public class UserEventsCreator {
    public static UserEvents createUserEventsToBeSaved(){
        return UserEvents.builder()
                .dateTime(LocalDateTime.parse("2022-02-07T16:15:57.24944"))
                .user(UserCreator.createValidUser())
                .event(EventCreator.createValidEvent())
                .build();
    }

    public static UserEvents createValidUserEvents(){
        return UserEvents.builder()
                .dateTime(LocalDateTime.parse("2022-02-06T16:15:57.24944"))
                .user(UserCreator.createValidUser())
                .event(EventCreator.createValidEvent())
                .build();
    }

    public static UserEvents createValidUpdatedUserEvents(){
        return UserEvents.builder()
                .dateTime(LocalDateTime.parse("2022-02-05T16:15:57.24944"))
                .user(UserCreator.createValidUser())
                .event(EventCreator.createValidEvent())
                .build();
    }
}
