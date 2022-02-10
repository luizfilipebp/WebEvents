package com.microservice.service;

import com.microservice.models.UserEvents;
import com.microservice.repository.UserEventsRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for UserEvents Service")
class UserEventsServiceTest {
    @InjectMocks
    private UserEventsService userEventsService;
    @Mock
    private UserEventsRepository userEventsRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<UserEvents> userEventsPage = new PageImpl<>(List.of(UserEventsCreator.createValidUserEvents()));

        BDDMockito.when(userEventsRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(userEventsPage);

        BDDMockito.when(userEventsRepositoryMock.findById(ArgumentMatchers.anyLong()))
               .thenReturn(Optional.of(UserEventsCreator.createValidUserEvents()));


        BDDMockito.when(userEventsRepositoryMock.save(ArgumentMatchers.any(UserEvents.class)))
                .thenReturn(UserEventsCreator.createValidUserEvents());

        BDDMockito.doNothing().when(userEventsRepositoryMock).delete(ArgumentMatchers.any(UserEvents.class));

    }

    @Test
    @DisplayName("ListAll, returns list of userEventss when successful")
    void list_ReturnsListOfUserEventss_WhenSuccessful(){
        LocalDateTime expectedTime = UserEventsCreator.createValidUserEvents().getDateTime();

        Page<UserEvents> userEventsPage = userEventsService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(userEventsPage).isNotNull();

        Assertions.assertThat(userEventsPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(userEventsPage.toList().get(0).getDateTime()).isEqualTo(expectedTime);
    }

    @Test
    @DisplayName("Save, returns userEvents when successful")
    void save_ReturnsUserEvents_WhenSuccessful() {
        UserEvents userEvents = userEventsService.save(UserEventsPostRequestBodyCreator.createUserEventsPostRequestBody());

        Assertions.assertThat(userEvents).isNotNull().isEqualTo(UserEventsCreator.createValidUserEvents());
    }

    @Test
    @DisplayName("Delete, removes userEvents when successful")
    void delete_RemovesUserEvents_WhenSuccessful() {

        Assertions.assertThatCode(() -> userEventsService.delete(1L))
                .doesNotThrowAnyException();
    }

}