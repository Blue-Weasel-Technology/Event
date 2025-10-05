# Description
---
**NearMe** is a Spring Boot web application that collects data and displays upcoming events in Romania on an interactive map.  
The platform uses web scraping, filtering, and geolocation to help users discover nearby events.

## Features
---
- **Event scraping:** Automatically collects event data from external sources using Selenium.  
- **Database:** Stores events in a PostgreSQL database.  
- **Interactive map:** Displays events on the map with custom markers and popups.  
- **Filtering:** Filter by name, location, style, and date range.  
- **Geolocation:** Finds the nearest events based on the user’s current location.  
- **Modern interface:** Responsive design with filters and detailed modals.

## Technologies Used
---
- Java 21, Spring Boot  
- Selenium WebDriver  
- PostgreSQL  
- Leaflet.js, jQuery, Moment.js  
- Lombok  
- Maven

# How the Application is Organized
---
The application has a main page where the user can access an interactive map and various options to filter events happening nearby.

![Main Page](image.png)

# Technical Details
---
- **Backend:** Java (Spring Boot)  
- **Frontend:** HTML, JavaScript & CSS  
- **Hosting:** ...

# Installation
---
- Clone the repository  
- Run the server:  
  ```bash
  mvn spring-boot:run
