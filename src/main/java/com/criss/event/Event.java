package com.criss.event;

import com.criss.event.date.Date;
import com.criss.event.location.Location;
import com.criss.event.ticket.TicketGroup;
import com.criss.event.user.Organizer;

public class Event {
    private String name;
    private Organizer organizer;
    private Location location;
    private Date date;
    private TicketGroup ticketgroup;
    private String description;

    public Event(){
        // Default constructor
    }

    public Event(String name, Organizer organizer, Location location, Date date, TicketGroup ticketgroup, String description) {
        this.name = name;
        this.organizer = organizer;
        this.location = location;
        this.date = date;
        this.ticketgroup = ticketgroup;
        this.description = description;
    }

    // #region Getters and Setters, used to Collapse Block
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Organizer getOrganizer() {
        return organizer;
    }
    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public TicketGroup getTicketgroup() {
        return ticketgroup;
    }
    public void setTicketgroup(TicketGroup ticketgroup) {
        this.ticketgroup = ticketgroup;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // #endregion
}
