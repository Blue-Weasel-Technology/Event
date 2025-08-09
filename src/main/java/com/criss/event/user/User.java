package com.criss.event.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to auto-generate getters, setters, toString(), equals(),
      // hashCode(), and constructors
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@EqualsAndHashCode(callSuper = false) // Ensure that equals() and hashCode() consider fields from the superclass
                                      // (User)
public class User {
    private String name;
    private int age;

}
