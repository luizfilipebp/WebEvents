package com.microservice.requests.Event;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor

public class EventPostRequestBody {
    @NotNull(message = "Event id cannot be null")
    @Schema(description = "This is the Event id", example = "1")
    private UUID id;
    @NotEmpty(message = "Event type cannot be empty")
    @Schema(description = "This is the event type", example = "click in buy product")
    private String type;
}
