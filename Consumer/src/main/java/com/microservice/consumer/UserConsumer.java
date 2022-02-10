package com.microservice.consumer;

import com.microservice.dto.UserEventsDto;
import com.microservice.models.Event;
import com.microservice.models.User;
import com.microservice.requests.Event.EventPutRequestBody;
import com.microservice.requests.User.UserPutRequestBody;
import com.microservice.requests.UserEvents.UserEventsPostRequestBody;
import com.microservice.service.EventService;
import com.microservice.service.UserEventsService;
import com.microservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConsumer{
    private final UserEventsService service;
    private final UserService userService;
    private final EventService eventService;

    @RabbitListener(queues = "EVENT")
    private void consumidor(UserEventsDto userDto){
        UserPutRequestBody userPut = new UserPutRequestBody(userDto.getUserId(),
                userDto.getUserNickName(),
                userDto.getUserRegistrationDate());
        User user = userService.findByIdOrCreate(userPut);

        EventPutRequestBody eventPut = new EventPutRequestBody(userDto.getEventId(), userDto.getEventType());
        Event event = eventService.findByIdOrCreate(eventPut);

        UserEventsPostRequestBody post = new UserEventsPostRequestBody(userDto.getEventDateTime(), user, event);
        service.save(post);
    }
}
