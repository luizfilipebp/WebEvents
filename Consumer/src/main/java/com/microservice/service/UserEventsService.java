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

@Service
@RequiredArgsConstructor

public class UserEventsService {
    private final UserEventsRepository repository;

    // FIND ONE
    public UserEvents findByIDOrThrowBadRequestException(Long id){
        return repository.findById(id).orElseThrow(() -> new BadRequestException("User event not Found"));
    }

    //CREATE
    @Transactional
    public UserEvents save(UserEventsPostRequestBody userEventsPostRequestBody) {
        return repository.save(UserEventsMapper.INSTANCE.toUserPost(userEventsPostRequestBody));
    }

    //READ
    public Page<UserEvents> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }


    //DELETE
    public void delete (Long id) {
        repository.delete(findByIDOrThrowBadRequestException(id));
    }

}