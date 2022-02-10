package com.microservice.requests.UserEvents;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEventsPutRequestBody {
    private Long id;
    private String type;
}
