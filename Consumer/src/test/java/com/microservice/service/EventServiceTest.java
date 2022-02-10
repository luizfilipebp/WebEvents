package com.microservice.service;

import com.microservice.exception.BadRequestException;
import com.microservice.models.Event;
import com.microservice.repository.EventRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Event Service")
class EventServiceTest {
    @InjectMocks
    private EventService eventService;
    @Mock
    private EventRepository eventRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Event> eventsPage = new PageImpl<>(List.of(EventCreator.createValidEvent()));

        BDDMockito.when(eventRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(eventsPage);

        BDDMockito.when(eventRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(EventCreator.createValidEvent()));


        BDDMockito.when(eventRepositoryMock.save(ArgumentMatchers.any(Event.class)))
                .thenReturn(EventCreator.createValidEvent());

        BDDMockito.doNothing().when(eventRepositoryMock).delete(ArgumentMatchers.any(Event.class));

    }

    @Test
    @DisplayName("ListAll, returns list of events when successful")
    void list_ReturnsListOfEvents_WhenSuccessful(){
        String expectedType = EventCreator.createValidEvent().getType();

        Page<Event> eventPage = eventService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(eventPage).isNotNull();

        Assertions.assertThat(eventPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(eventPage.toList().get(0).getType()).isEqualTo(expectedType);
    }

    @Test
    @DisplayName("Save, returns event when successful")
    void save_ReturnsEvent_WhenSuccessful() {
        Event event = eventService.save(EventPostRequestBodyCreator.createEventPostRequestBody());

        Assertions.assertThat(event).isNotNull().isEqualTo(EventCreator.createValidEvent());
    }

    @Test
    @DisplayName("findById, returns event when successful")
    void findById_ReturnsEvent_WhenSuccessful() {
        Long expectedId = EventCreator.createValidEvent().getId();

        Event event = eventService.findByIDOrThrowBadRequestException(1L);

        Assertions.assertThat(event).isNotNull();
        Assertions.assertThat(event.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIDOrThrowBadRequestException, Throws bad request exception when event is not found")
    void findByIDOrThrowBadRequestException_ThrowsBadRequestException_WhenEventIsNotFound() {

        BDDMockito.when(eventRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> eventService.findByIDOrThrowBadRequestException(1L));

    }

    @Test
    @DisplayName("Delete, removes event when successful")
    void delete_RemovesEvent_WhenSuccessful() {

        Assertions.assertThatCode(() -> eventService.delete(1L))
                .doesNotThrowAnyException();
    }

}