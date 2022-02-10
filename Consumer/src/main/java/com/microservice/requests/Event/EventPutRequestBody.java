package com.microservice.requests.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EventPutRequestBody {
    private Long id;
    private String type;
}
