package com.microservice.util.user;

import com.microservice.requests.User.UserPutRequestBody;

public class UserPutRequestBodyCreator {
    public static UserPutRequestBody createUserPutRequestBody(){
        return UserPutRequestBody.builder()
                .id(UserCreator.createValidUpdatedUser().getId())
                .name(UserCreator.createValidUpdatedUser().getName())
                .build();
    }
}
