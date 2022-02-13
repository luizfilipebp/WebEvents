package com.microservice.requests.UserEvents;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserEventsPutRequestBody {
    private UUID id;
    private String type;
}
