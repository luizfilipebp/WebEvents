package com.microservice.mapper;

import com.microservice.models.UserEvents;
import com.microservice.requests.UserEvents.UserEventsPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserEventsMapper {
    public static final UserEventsMapper INSTANCE = Mappers.getMapper(UserEventsMapper.class);

    public abstract UserEvents toUserEvents (UserEventsPostRequestBody userEventsPostRequestBody);

}
