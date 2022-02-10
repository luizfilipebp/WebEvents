package com.microservice.requests.UserEvents;

import com.microservice.models.Event;
import com.microservice.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor

public class UserEventsPostRequestBody {
    @NotNull(message = "User Events datetime cannot be null")
    @Schema(description = "This is the time when the user event occurred", example = "2022-02-08T16:18:05.485Z")
    private LocalDateTime dateTime;

    @NotNull(message = "The User cannot be empty")
    @Schema(description = "This is the user who made the event")
    private User user;

    @NotNull(message = "The Event cannot be empty")
    @Schema(description = "This is the event the user made")
    private Event event;

}
