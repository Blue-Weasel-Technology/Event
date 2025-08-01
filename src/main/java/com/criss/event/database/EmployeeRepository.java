package com.criss.event.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    // No additional methods needed here - CRUD operations (save, find, delete, etc.) are already provided by CrudRepository
}
