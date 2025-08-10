package com.criss.event.database;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    // Controller to handle employee repository
    private final EventRepository eventRepository;

    // Constructor injection: Spring injects the employeeRepository here
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // GET request to fetch all employees
    @GetMapping("/events")
    public Iterable<Event> findAllEvents() {
        // Fetches all employee records from the database using the repository
        return eventRepository.findAll();
    }

}