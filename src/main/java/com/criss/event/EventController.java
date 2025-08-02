package com.criss.event;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    
    // Controller to handle employee repository, acting as a middle-man between HTTP requests and database actions
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

    // POST request to add a new employee
    @PostMapping("/events")
    public Event addOneEvent(@RequestBody Event event) {
        // Saves the incoming employee object to the database and returns it
        return this.eventRepository.save(event);
    }

}