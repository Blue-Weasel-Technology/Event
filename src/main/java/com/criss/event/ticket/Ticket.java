package com.criss.event.ticket;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data               // Lombok annotation to auto-generate getters, setters, toString(), equals(), hashCode(), and constructors
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
public class Ticket {
    public double price;
    public String currency;
    public String type; //Ex. VIP, Standard, 3-day pass etc.
    public int quantity; 
}