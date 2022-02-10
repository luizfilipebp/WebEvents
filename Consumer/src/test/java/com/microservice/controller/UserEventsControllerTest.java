package com.microservice.controller;

import com.microservice.models.UserEvents;
import com.microservice.requests.UserEvents.UserEventsPostRequestBody;
import com.microservice.service.UserEventsService;
import com.microservice.util.UserEvents.UserEventsCreator;
import com.microservice.util.UserEvents.UserEventsPostRequestBodyCreator;
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

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
class UserEventsControllerTest {
    @InjectMocks
    private UserEventsController userEventsController;
    @Mock
    private UserEventsService userEventsServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<UserEvents> userEventsPage = new PageImpl<>(List.of(UserEventsCreator.createValidUserEvents()));
        BDDMockito.when(userEventsServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(userEventsPage);

        BDDMockito.when(userEventsServiceMock.save(ArgumentMatchers.any(UserEventsPostRequestBody.class)))
                .thenReturn(UserEventsCreator.createValidUserEvents());

        //BDDMockito.doNothing().when(userEventsServiceMock).replace(ArgumentMatchers.any(UserEventsPutRequestBody.class));

        BDDMockito.doNothing().when(userEventsServiceMock).delete(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("List, returns list of user events inside page object when successful")
    void list_ReturnsListOfUserEventsInsidePageObject_WhenSuccessful(){
        LocalDateTime expectedTime = UserEventsCreator.createValidUserEvents().getDateTime();
        Page<UserEvents> userEventsPage = userEventsController.list(null).getBody();

        Assertions.assertThat(userEventsPage).isNotNull();

        Assertions.assertThat(userEventsPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(userEventsPage.toList().get(0).getDateTime()).isEqualTo(expectedTime);
    }

    @Test
    @DisplayName("ListAll, returns list of userEvents when successful")
    void list_ReturnsListOfUserEvents_WhenSuccessful(){
        LocalDateTime expectedTime = UserEventsCreator.createValidUserEvents().getDateTime();
        Page<UserEvents> userEventsPage = userEventsController.list(null).getBody();

        Assertions.assertThat(userEventsPage).isNotNull();

        Assertions.assertThat(userEventsPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(userEventsPage.toList().get(0).getDateTime()).isEqualTo(expectedTime);
    }

    @Test
    @DisplayName("Save, returns userEvents when successful")
    void save_ReturnsUserEvents_WhenSuccessful() {
        LocalDateTime expectedTimem = UserEventsCreator.createValidUserEvents().getDateTime();

        UserEvents userEvents = userEventsController.save(UserEventsPostRequestBodyCreator.createUserEventsPostRequestBody()).getBody();

        Assertions.assertThat(userEvents).isNotNull().isEqualTo(UserEventsCreator.createValidUserEvents());
    }

    @Test
    @DisplayName("Delete, removes userEvents when successful")
    void delete_RemovesUserEvents_WhenSuccessful() {

        Assertions.assertThatCode(() -> userEventsController.delete(1L))
                .doesNotThrowAnyException();


        ResponseEntity<Void> entity = userEventsController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}