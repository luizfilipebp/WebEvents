package com.microservice.util.user;

import com.microservice.requests.User.UserPostRequestBody;

public class UserPostRequestBodyCreator {
    public static UserPostRequestBody createUserPostRequestBody(){
        return UserPostRequestBody.builder()
                .name(UserCreator.createUserToBeSaved().getName())
                .build();
    }
}
