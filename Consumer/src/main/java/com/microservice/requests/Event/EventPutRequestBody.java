package com.microservice.requests.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class EventPutRequestBody {
    private UUID id;
    private String type;
}
