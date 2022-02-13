package com.microservice.service;

import com.microservice.exception.BadRequestException;
import com.microservice.mapper.UserEventsMapper;
import com.microservice.models.UserEvents;
import com.microservice.repository.UserEventsRepository;
import com.microservice.requests.UserEvents.UserEventsPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor

public class UserEventsService {
    private final UserEventsRepository repository;

    // FIND ONE
    public UserEvents findByIDOrThrowBadRequestException(UUID id){
        return repository.findById(id).orElseThrow(() -> new BadRequestException("User event not Found"));
    }

    //CREATE
    @Transactional
    public UserEvents save(UserEventsPostRequestBody userEventsPostRequestBody) {
        return repository.save(UserEventsMapper.INSTANCE.toUserEvents(userEventsPostRequestBody));
    }

    //READ
    public Page<UserEvents> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }


    //DELETE
    public void delete (UUID id) {
        repository.delete(findByIDOrThrowBadRequestException(id));
    }

}