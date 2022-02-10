package com.microservice.controller;

import com.microservice.models.User;
import com.microservice.requests.User.UserPostRequestBody;
import com.microservice.requests.User.UserPutRequestBody;
import com.microservice.service.UserService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<User> usersPage = new PageImpl<>(List.of(UserCreator.createValidUser()));
        BDDMockito.when(userServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(usersPage);

        BDDMockito.when(userServiceMock.save(ArgumentMatchers.any(UserPostRequestBody.class)))
                .thenReturn(UserCreator.createValidUser());

        BDDMockito.doNothing().when(userServiceMock).replace(ArgumentMatchers.any(UserPutRequestBody.class));

        BDDMockito.doNothing().when(userServiceMock).delete(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("List, returns list of user inside page object when successful")
    void list_ReturnsListOfUsersInsidePageObject_WhenSuccessful(){
        String expectedName = UserCreator.createValidUser().getName();
        Page<User> userPage = userController.list(null).getBody();

        Assertions.assertThat(userPage).isNotNull();

        Assertions.assertThat(userPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(userPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("ListAll, returns list of user when successful")
    void list_ReturnsListOfUsers_WhenSuccessful(){
        String expectedName = UserCreator.createValidUser().getName();
        Page<User> userPage = userController.list(null).getBody();

        Assertions.assertThat(userPage).isNotNull();

        Assertions.assertThat(userPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(userPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Save, returns user when successful")
    void save_ReturnsUser_WhenSuccessful() {
        Long expectedId = UserCreator.createValidUser().getId();

        User user = userController.save(UserPostRequestBodyCreator.createUserPostRequestBody()).getBody();

        Assertions.assertThat(user).isNotNull().isEqualTo(UserCreator.createValidUser());
    }

    @Test
    @DisplayName("Replace, update user when successful")
    void replace_UpdateUser_WhenSuccessful() {

        Assertions.assertThatCode(() -> userController.replace(UserPutRequestBodyCreator.createUserPutRequestBody()))
                .doesNotThrowAnyException();


        ResponseEntity<Void> entity = userController.replace(UserPutRequestBodyCreator.createUserPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("Delete, removes user when successful")
    void delete_RemovesUser_WhenSuccessful() {

        Assertions.assertThatCode(() -> userController.delete(1L))
                .doesNotThrowAnyException();


        ResponseEntity<Void> entity = userController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}