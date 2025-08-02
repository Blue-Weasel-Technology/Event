package com.criss.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
    // No additional methods needed here - CRUD operations (save, find, delete, etc.) are already provided by CrudRepository
}
