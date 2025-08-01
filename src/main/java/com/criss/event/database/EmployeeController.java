package com.criss.event.database;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    
    // Controller to handle employee repository, acting as a middle-man between HTTP requests and database actions
    private final EmployeeRepository employeeRepository;

    // Constructor injection: Spring injects the employeeRepository here
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET request to fetch all employees
    @GetMapping("/employees")
    public Iterable<Employee> findAllEmployees() {
        // Fetches all employee records from the database using the repository
        return employeeRepository.findAll();
    }

    // POST request to add a new employee
    @PostMapping("/employees")
    public Employee addOneEmployee(@RequestBody Employee employee) {
        // Saves the incoming employee object to the database and returns it
        return this.employeeRepository.save(employee);
    }

}
