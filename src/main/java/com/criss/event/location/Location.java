package com.criss.event.location;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data               // Lombok annotation to auto-generate getters, setters, toString(), equals(), hashCode(), and constructors
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
public class Location {
    private String city;
    private String address;
    private double latitude, longitude;
    private String venueName;
    private String type; // Ex. restaurant, bar, etc

}
