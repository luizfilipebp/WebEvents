package com.microservice.repository;

import com.microservice.models.Event;
import com.microservice.util.event.EventCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Event Repository")
class EventRepositoryTest {
    @Autowired
    private EventRepository EventRepository;

    @Test
    @DisplayName("Save, persists Event when successful")
    public void save_PersistEvent_WhenSuccessful() {
        Event EventToBeSaved = EventCreator.createEventToBeSaved();
        Event savedEvent = this.EventRepository.save(EventToBeSaved);
        Assertions.assertThat(savedEvent).isNotNull();
        Assertions.assertThat(savedEvent.getId()).isNotNull();
        Assertions.assertThat(savedEvent.getType()).isEqualTo(EventToBeSaved.getType());
    }

    @Test
    @DisplayName("Save, updates Event when successful")
    public void save_UpdatesEvent_WhenSuccessful() {
        Event eventToBeSaved = EventCreator.createEventToBeSaved();
        Event eventSaved = this.EventRepository.save(eventToBeSaved);

        eventSaved.setType("SaveUpdatesTest");

        Event eventUpdated = this.EventRepository.save(eventSaved);

        Assertions.assertThat(eventUpdated).isNotNull();
        Assertions.assertThat(eventUpdated.getId()).isNotNull();
        Assertions.assertThat(eventUpdated.getType()).isEqualTo(eventSaved.getType());
    }

    @Test
    @DisplayName("Delete, removes Event when successful")
    public void delete_RemovesEvent_WhenSuccessful() {
        Event eventToBeSaved = EventCreator.createEventToBeSaved();
        Event eventSaved = this.EventRepository.save(eventToBeSaved);

        this.EventRepository.delete(eventSaved);
        Optional<Event> EventOptional = this.EventRepository.findById(eventSaved.getId());
        Assertions.assertThat(EventOptional).isEmpty();
    }
}
