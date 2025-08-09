package com.criss.event.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to auto-generate getters, setters, toString(), equals(),
      // hashCode(), and constructors
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class Organizer extends User {
    private String contactEmail;

    // Manually overriding Lambok equals() method
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj)) // Calls the superclass's equals() method (from User class)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Organizer other = (Organizer) obj;
        if (contactEmail == null) {
            if (other.contactEmail != null)
                return false;
        } else if (!contactEmail.equals(other.contactEmail))
            return false;
        return true;
    }

    // Manually overriding Lambok hashCode() method
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode(); // Calls the superclass's hashCode() method (from User class)
        result = prime * result + ((contactEmail == null) ? 0 : contactEmail.hashCode());
        return result;
    }
}