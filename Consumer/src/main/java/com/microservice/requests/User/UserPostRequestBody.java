package com.microservice.requests.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor

public class UserPostRequestBody {
    @NotNull(message = "User id cannot be null")
    @Schema(description = "This is the User id", example = "1")
    private UUID id;

    @NotEmpty(message = "User name cannot be empty")
    @Schema(description = "This is the User name", example = "Filipe")
    private String name;

    @NotNull(message = "User registration date cannot be null")
    @Schema(description = "This is the User registration date", example = "1998-09-24T16:18:05.485Z")
    private Date registrationDate;
}
