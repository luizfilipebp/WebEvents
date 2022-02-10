package com.microservice.controller;

import com.microservice.models.UserEvents;
import com.microservice.requests.UserEvents.UserEventsPostRequestBody;
import com.microservice.service.UserEventsService;
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

@RestController
@RequestMapping("/events")
@Log4j2
@RequiredArgsConstructor

public class UserEventsController {
    private final UserEventsService service;

    @Operation(summary = "List all User Events paginated", tags = {"UserEvents"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
    })
    @GetMapping
    public ResponseEntity<Page<UserEvents>> list(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(service.listAll(pageable));
    }

    @Operation(summary = "Register a new User Events", tags = {"UserEvents"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request check the fields message"),
    })
    @PostMapping
    public ResponseEntity<UserEvents> save(@RequestBody @Valid UserEventsPostRequestBody userEventsPostRequestBody){
        return new ResponseEntity<>(service.save(userEventsPostRequestBody), HttpStatus.CREATED);
    }


    @Operation(summary = "Delete a specific user", tags = {"UserEvents"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "User not exists in the Databases"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}