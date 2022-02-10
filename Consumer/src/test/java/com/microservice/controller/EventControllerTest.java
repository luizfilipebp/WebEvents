package com.microservice.controller;

import com.microservice.models.Event;
import com.microservice.requests.Event.EventPostRequestBody;
import com.microservice.service.EventService;
import com.microservice.util.event.EventCreator;
import com.microservice.util.event.EventPostRequestBodyCreator;
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
class EventControllerTest {
    @InjectMocks
    private EventController eventController;
    @Mock
    private EventService eventServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Event> eventsPage = new PageImpl<>(List.of(EventCreator.createValidEvent()));
        BDDMockito.when(eventServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(eventsPage);

        BDDMockito.when(eventServiceMock.save(ArgumentMatchers.any(EventPostRequestBody.class)))
                .thenReturn(EventCreator.createValidEvent());

        BDDMockito.doNothing().when(eventServiceMock).delete(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("List, returns list of Event inside page object when successful")
    void list_ReturnsListOfEventsInsidePageObject_WhenSuccessful(){
        String expectedType = EventCreator.createValidEvent().getType();
        Page<Event> EventPage = eventController.list(null).getBody();

        Assertions.assertThat(EventPage).isNotNull();

        Assertions.assertThat(EventPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(EventPage.toList().get(0).getType()).isEqualTo(expectedType);
    }

    @Test
    @DisplayName("ListAll, returns list of Event when successful")
    void list_ReturnsListOfEvents_WhenSuccessful(){
        String expectedType = EventCreator.createValidEvent().getType();
        Page<Event> EventPage = eventController.list(null).getBody();

        Assertions.assertThat(EventPage).isNotNull();

        Assertions.assertThat(EventPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(EventPage.toList().get(0).getType()).isEqualTo(expectedType);
    }

    @Test
    @DisplayName("Save, returns Event when successful")
    void save_ReturnsEvent_WhenSuccessful() {
        Long expectedId = EventCreator.createValidEvent().getId();

        Event Event = eventController.save(EventPostRequestBodyCreator.createEventPostRequestBody()).getBody();

        Assertions.assertThat(Event).isNotNull().isEqualTo(EventCreator.createValidEvent());
    }

    @Test
    @DisplayName("Delete, removes Event when successful")
    void delete_RemovesEvent_WhenSuccessful() {

        Assertions.assertThatCode(() -> eventController.delete(1L))
                .doesNotThrowAnyException();


        ResponseEntity<Void> entity = eventController.delete(1L);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}