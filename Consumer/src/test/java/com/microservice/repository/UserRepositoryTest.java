package com.microservice.repository;

import com.microservice.models.User;
import com.microservice.util.user.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for User Repository")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Save, persists user when successful")
    public void save_PersistUser_WhenSuccessful(){
        User userToBeSaved =  UserCreator.createUserToBeSaved();
        User savedUser = this.userRepository.save(userToBeSaved);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isNotNull();
        Assertions.assertThat(savedUser.getName()).isEqualTo(userToBeSaved.getName());
    }

    @Test
    @DisplayName("Save, updates user when successful")
    public void save_UpdatesUser_WhenSuccessful(){
        User userToBeSaved =  UserCreator.createUserToBeSaved();
        User userSaved = this.userRepository.save(userToBeSaved);

        userSaved.setName("SaveUpdatesTest");

        User userUpdated = this.userRepository.save(userSaved);

        Assertions.assertThat(userUpdated).isNotNull();
        Assertions.assertThat(userUpdated.getId()).isNotNull();
        Assertions.assertThat(userUpdated.getName()).isEqualTo(userSaved.getName());
    }

    @Test
    @DisplayName("Delete, removes user when successful")
    public void delete_RemovesUser_WhenSuccessful(){
        User userToBeSaved =  UserCreator.createUserToBeSaved();
        User userSaved = this.userRepository.save(userToBeSaved);

        this.userRepository.delete(userSaved);
        Optional<User> userOptional = this.userRepository.findById(userSaved.getId());
        Assertions.assertThat(userOptional).isEmpty();
    }
}