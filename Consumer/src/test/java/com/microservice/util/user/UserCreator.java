package com.microservice.util.user;

import com.microservice.models.User;

import java.time.LocalDateTime;

public class UserCreator {
    public static User createUserToBeSaved(){
        return User.builder()
                .name("usurious")
                .id(1L)
                .registrationDate(LocalDateTime.parse("2022-02-06T16:15:57.24944"))
                .build();
    }

    public static User createValidUser(){
        return User.builder()
                .name("usurious")
                .id(1L)
                .registrationDate(LocalDateTime.parse("2022-02-06T16:15:57.24944"))
                .build();
    }

    public static User createValidUpdatedUser(){
        return User.builder()
                .name("usuriousChanged")
                .id(1L)
                .registrationDate(LocalDateTime.now())
                .build();
    }
}
