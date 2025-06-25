package com.criss.event.ticket;

import java.util.ArrayList;
import java.util.List;

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

    // #region Getters and Setters, used to Collapse Block
    public List<Ticket> getTicketgroup() {
        return ticketgroup;
    }
    public void setTicketgroup(List<Ticket> ticketgroup) {
        this.ticketgroup = ticketgroup;
    }
    // #endregion
}
