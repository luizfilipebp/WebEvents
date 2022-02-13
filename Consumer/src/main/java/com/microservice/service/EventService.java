package com.microservice.service;

import com.microservice.exception.BadRequestException;
import com.microservice.mapper.EventMapper;
import com.microservice.models.Event;
import com.microservice.repository.EventRepository;
import com.microservice.requests.Event.EventPostRequestBody;
import com.microservice.requests.Event.EventPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class EventService {
    private final EventRepository repository;

    // FIND ONE
    public Event findByIDOrThrowBadRequestException(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Event not Found"));
    }

    public Event findByIdOrCreate(EventPutRequestBody eventPutRequestBody){
        Optional<Event> result = repository.findById(eventPutRequestBody.getId());

        if (result.isEmpty()){
            Event event = new Event(eventPutRequestBody.getId(), eventPutRequestBody.getType());
            return repository.save(event);
        }
        return EventMapper.INSTANCE.toEvent(eventPutRequestBody);
    }

    //CREATE
    @Transactional
    public Event save(EventPostRequestBody eventPostRequestBody) {
        return repository.save(EventMapper.INSTANCE.toEvent(eventPostRequestBody));
    }

    @Transactional
    public Event saveWithId(EventPutRequestBody eventPutRequestBody) {
        return repository.save(EventMapper.INSTANCE.toEvent(eventPutRequestBody));
    }

    //READ
    public Page<Event> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }


    //DELETE
    public void delete (UUID id) {
        repository.delete(findByIDOrThrowBadRequestException(id));
    }


}