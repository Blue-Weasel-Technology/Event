package com.criss.event;


import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data               // Lombok annotation to auto-generate getters, setters, toString(), equals(), hashCode(), and constructors
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
@Entity             // Marks this class as a JPA entity, to be mapped to a table in the database
@Table(name = "events")  // Specifies the table name for this entity in the database
public class Event {

    @Id  // Marks this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-increments the ID, handled by Postgres
    private Integer id;

    private String name;  // Name of the event
    private String organizer;  // Just the name of the organizer (not linked to a class)

    private String locationDescription;  // Description of the event location
    private double latitude;  // Latitude of the location
    private double longitude;  // Longitude of the location

    private LocalDateTime eventDateTime;  // Combined date and time (including hour and minute)

    private String description;  // Event description
}
