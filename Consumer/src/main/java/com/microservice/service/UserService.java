package com.microservice.service;

import com.microservice.exception.BadRequestException;
import com.microservice.mapper.UserMapper;
import com.microservice.models.User;
import com.microservice.repository.UserRepository;
import com.microservice.requests.User.UserPostRequestBody;
import com.microservice.requests.User.UserPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    // FIND ONE
    public User findByIDOrThrowBadRequestException(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not Found"));
    }

    public User findByIdOrCreate(UserPutRequestBody userPutRequestBody){
        Optional<User> result = userRepository.findById(userPutRequestBody.getId());

        if (result.isEmpty()){
            User user = new User(userPutRequestBody.getId(),
                    userPutRequestBody.getName(),
                    userPutRequestBody.getRegistrationDate());
            return userRepository.save(user);
        }
        return UserMapper.INSTANCE.toUser(userPutRequestBody);
    }

    //CREATE
    @Transactional
    public User save(UserPostRequestBody userPostRequestBody) {
        return userRepository.save(UserMapper.INSTANCE.toUser(userPostRequestBody));
    }

    @Transactional
    public User saveWithId(UserPutRequestBody userPutRequestBody) {
        return userRepository.save(UserMapper.INSTANCE.toUser(userPutRequestBody));
    }


    //READ
    public Page<User> listAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    //UPDATE
    public void replace(UserPutRequestBody userPutRequestBody){
        User savedUser = findByIDOrThrowBadRequestException(userPutRequestBody.getId());
        User user = UserMapper.INSTANCE.toUser(userPutRequestBody);
        user.setId(savedUser.getId());
        userRepository.save(user);
    }

    //DELETE
    public void delete (UUID id) {
        userRepository.delete(findByIDOrThrowBadRequestException(id));
    }

}