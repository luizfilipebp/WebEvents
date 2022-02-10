package com.microservice.controller;

import com.microservice.dto.UserEventsDto;
import com.microservice.connection.RabbitMqConnection;
import com.microservice.service.RabbitMqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class UserController {
    @Autowired
    RabbitMqService mqService;


    @Operation(summary = "Register a new user event in the QUEUE user", tags = {"UserEvents"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation"),
    })
    @PostMapping
    private ResponseEntity alterUser(@RequestBody UserEventsDto userEventsDto){
        this.mqService.sendMessage(RabbitMqConnection.FILA_EVENT, userEventsDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
