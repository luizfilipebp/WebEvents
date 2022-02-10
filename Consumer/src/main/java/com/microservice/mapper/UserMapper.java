package com.microservice.mapper;

import com.microservice.models.User;
import com.microservice.requests.User.UserPostRequestBody;
import com.microservice.requests.User.UserPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostRequestBody userPostRequestBody);
    public abstract User toUser(UserPutRequestBody userPutRequestBody);
}
