package com.criss.event.database;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok annotation to auto-generate getters, setters, toString(), equals(), hashCode(), and constructors
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
@Entity             // Marks this class as a JPA entity, to be mapped to a table in the database
@Table(name = "employees")  // Specifies the table name for this entity in the database
public class Employee {

    @Id  // Marks this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-increments the ID, handled by Postgres
    private Integer id;

    private String firstName;  // Employee's first name
    private String lastName;   // Employee's last name
    private LocalDate dateOfBirth;  // Employee's date of birth

}
