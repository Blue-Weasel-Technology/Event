Setup:

Lombok:
- Lombok is included to make the code cleaner.
- It automatically generates Getters, Setters, Constructors, Hashcode, and Equals methods during runtime.

Database Setup:
- PostgreSQL is used, and the events table is created automatically. (Port 5432!!)
- Events are added from iabilete, a site the scraper gathers data from and sends it to the database.
- The scraper uses Nominatim to transform location descriptions into geographic coordinates (latitude and longitude), which are then displayed on the map.

Database Connection:
In your application.properties, fill in your Postgres creds like this:
- spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
- spring.datasource.username=your_username
- spring.datasource.password=your_password

Additional Details:
- The scraper and database integration has been added so that scraped events are automatically saved to the database.
- Locations from scraped events are transformed into geographic coordinates and shown on the map.
- We still need to improve the scraper because it currently ignores some events from the source list.
