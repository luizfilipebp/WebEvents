package com.microservice.service;

import com.microservice.exception.BadRequestException;
import com.microservice.models.User;
import com.microservice.repository.UserRepository;
import com.microservice.util.user.UserCreator;
import com.microservice.util.user.UserPostRequestBodyCreator;
import com.microservice.util.user.UserPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for User Service")
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<User> usersPage = new PageImpl<>(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(usersPage);

        BDDMockito.when(userRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(UserCreator.createValidUser()));


        BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any(User.class)))
                .thenReturn(UserCreator.createValidUser());

        BDDMockito.doNothing().when(userRepositoryMock).delete(ArgumentMatchers.any(User.class));

    }

    @Test
    @DisplayName("ListAll, returns list of users when successful")
    void list_ReturnsListOfUsers_WhenSuccessful(){
        String expectedName = UserCreator.createValidUser().getName();

        Page<User> userPage = userService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(userPage).isNotNull();

        Assertions.assertThat(userPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(userPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Save, returns user when successful")
    void save_ReturnsUser_WhenSuccessful() {
        User user = userService.save(UserPostRequestBodyCreator.createUserPostRequestBody());

        Assertions.assertThat(user).isNotNull().isEqualTo(UserCreator.createValidUser());
    }

    @Test
    @DisplayName("findById, returns user when successful")
    void findById_ReturnsUser_WhenSuccessful() {
        Long expectedId = UserCreator.createValidUser().getId();

        User user = userService.findByIDOrThrowBadRequestException(1L);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIDOrThrowBadRequestException, Throws bad request exception when user is not found")
    void findByIDOrThrowBadRequestException_ThrowsBadRequestException_WhenUserIsNotFound() {

        BDDMockito.when(userRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> userService.findByIDOrThrowBadRequestException(1L));

    }


    @Test
    @DisplayName("Replace, update user when successful")
    void replace_UpdateUser_WhenSuccessful() {

        Assertions.assertThatCode(() -> userService.replace(UserPutRequestBodyCreator.createUserPutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete, removes user when successful")
    void delete_RemovesUser_WhenSuccessful() {

        Assertions.assertThatCode(() -> userService.delete(1L))
                .doesNotThrowAnyException();
    }

}