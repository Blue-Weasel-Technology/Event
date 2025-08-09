Setup:

Selenium Web Scraping

This project includes Selenium for web scraping purposes. Currently, it scrapes data from a specified web source and displays the results directly in the terminal. No front-end display is implemented yet.

Lombok:
- Lombok is included to make the `Event` class cleaner.
- It automatically generates Getters, Setters, Constructors, Hashcode, and Equals methods during runtime.

Database Setup:
- PostgreSQL is used, and the `events` table is created automatically. (Port 5432!!)
- JSON gathering for events is **temporarily disabled**.
- To add events, just use this command in PowerShell:

Invoke-WebRequest -Uri "http://localhost:8080/events" -Method Post -Body '{"name": "Music Festival", "organizer": "John Doe", "locationDescription": "Central Park, NYC", "latitude": 40.785091, "longitude": -73.968285, "eventDatetime": "2023-08-15T18:00:00", "description": "A fun and exciting music festival featuring top artists."}' -Headers @{"Content-Type"="application/json"}

Database Connection:
- In your `application.properties`, fill in your Postgres creds like this:

spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name

spring.datasource.username=your_username

spring.datasource.password=your_password
