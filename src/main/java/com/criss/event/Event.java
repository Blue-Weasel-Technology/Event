package com.criss.event;

import com.criss.event.date.Date;
import com.criss.event.location.Location;
import com.criss.event.ticket.TicketGroup;
import com.criss.event.user.Organizer;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data               // Lombok annotation to auto-generate getters, setters, toString(), equals(), hashCode(), and constructors
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
public class Event {
    private String name;
    private Organizer organizer;
    private Location location;
    private Date date;
    private TicketGroup ticketgroup;
    private String description;

}
