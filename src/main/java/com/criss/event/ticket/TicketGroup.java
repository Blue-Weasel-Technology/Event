package com.criss.event.ticket;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data               // Lombok annotation to auto-generate getters, setters, toString(), equals(), hashCode(), and constructors
public class TicketGroup {
    private List<Ticket> ticketgroup;

    public TicketGroup() {
        this.ticketgroup = new ArrayList<>();
    }
    public void addTicket(Ticket ticket) {
        ticketgroup.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        ticketgroup.remove(ticket);
    }
}
