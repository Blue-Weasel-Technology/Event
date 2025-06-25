package com.criss.event;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.criss.event.ticket.Ticket;
import java.io.InputStream;

@SpringBootApplication
public class EventApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
		InputStream jsonStream = EventApplication.class.getClassLoader().getResourceAsStream("static/events.json");
		List<Event> events = EventLoader.loadEventsFromStream(jsonStream);
		
		for (Event event : events) {
            System.out.println("Event Name: " + event.getName());
            System.out.println("Description: " + event.getDescription());
            System.out.println("Date: " + event.getDate().getDate());
            System.out.println("Location: " + event.getLocation().getCity() + ", " + event.getLocation().getAddress());
            System.out.println("Organizer: " + event.getOrganizer().getName());
			for (Ticket ticket : event.getTicketgroup().getTicketgroup()) {
				System.out.println("  - Type: " + ticket.getType() + ", Price: " + ticket.getPrice() + " " + ticket.getCurrency() + ", Quantity: " + ticket.getQuantity());
			}
            System.out.println("--------------------------------");
        }
    }

}


