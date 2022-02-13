package com.microservice.repository;

import com.microservice.models.UserEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserEventsRepository extends JpaRepository<UserEvents, UUID> {
}
