package com.microservice.controller;

import com.microservice.models.Event;
import com.microservice.requests.Event.EventPostRequestBody;
import com.microservice.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/event")
@Log4j2
@RequiredArgsConstructor

public class EventController {
    private final EventService service;

    @Operation(summary = "List all events paginated", tags = {"Event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
    })
    @GetMapping
    public ResponseEntity<Page<Event>> list(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(service.listAll(pageable));
    }


    @Operation(summary = "Register a new event", tags = {"Event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request check the fields message"),
    })
    @PostMapping
    public ResponseEntity<Event> save(@RequestBody @Valid EventPostRequestBody eventPostRequestBody){
        return new ResponseEntity<>(service.save(eventPostRequestBody), HttpStatus.CREATED);
    }


    @Operation(summary = "Delete a specific event", tags = {"Event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Event not exists in the Databases"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}