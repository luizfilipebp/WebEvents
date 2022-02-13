package com.microservice.controller;

import com.microservice.models.User;
import com.microservice.requests.User.UserPostRequestBody;
import com.microservice.requests.User.UserPutRequestBody;
import com.microservice.service.UserService;
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
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @Operation(summary = "List all users paginated", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
    })
    @GetMapping
    public ResponseEntity<Page<User>> list(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(userService.listAll(pageable));
    }


    @Operation(summary = "Register a new user", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request check the fields message"),
    })
    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserPostRequestBody userPostRequestBody){
        return new ResponseEntity<>(userService.save(userPostRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Replace a specific user", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request check the fields message"),
    })
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid UserPutRequestBody userPutRequestBody){
        userService.replace(userPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Delete a specific user", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "User not exists in the Databases"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}