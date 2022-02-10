package com.microservice.mapper;

import com.microservice.models.Event;
import com.microservice.requests.Event.EventPostRequestBody;
import com.microservice.requests.Event.EventPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class EventMapper {
    public static final EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    public abstract Event toEventPost (EventPostRequestBody eventPostRequestBody);
    public abstract Event toEventPut (EventPutRequestBody eventPutRequestBody);
}
