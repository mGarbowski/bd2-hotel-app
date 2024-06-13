
# Hotel app

* Mikołaj Garbowski
* Michał Łuszczek
* Maksym Bieńkowski
* Jędrzej Grabski
* Aleksander Drwal

Semester project for the Databases 2 course (2024L)

## Project Description
The project consists of a database and applications supporting the hotel reservation system.

* [Data Model](./docs/data-model.md)
* [Build and Run Instructions](./docs/build-and-run.md)
* [App Structure](./docs/app-structure.md)
* [End User Documentation](./docs/end-user-documentation.md)
* [Database documentation](./docs/database-structure.md)
* [End user test scenarios](./docs/test-scenarios.md)

## Features
* Operational
  * Searching for available rooms in a selected area by the client
  * Making a reservation by the client
  * Displaying the client's active reservations
  * Ordering an additional service by the client
  * Displaying the amount to be paid by the client
  * Simulated payment of dues by the client
  * Posting a review by the client
  * Submitting a complaint by the client
* Analytical and Reporting
  * Displaying a summary of the number of reservations by hotel
  * Displaying a summary of revenue by hotel
  * Displaying a ranking of hotels based on aggregated ratings
  * Displaying a summary of the number of complaints by hotel

## Documentation
The code documentation has been generated using JavaDocs.
It can be accessed by opening the [index.html](docs/javadocs/index.html) file in the `docs/javadocs` folder.

## Technologies Used
* PostgreSQL database
* Application using the Spring Framework
  * Spring Data JPA ORM
  * Spring Shell module for creating CLI applications
* ER and logical model - Oracle Data Modeler

## Division of Work:

* User Interface Skeleton - Mikołaj Garbowski
* Database Schema – The Entire Team
* SQL Scripts – Mikołaj Garbowski
* SQL Triggers/Procedures - Michał Łuszczek, Maksym Bieńkowski
* Application Functionality Implementation – Mikołaj Garbowski/Jędrzej Grabski/Aleksander Drwal/Maksym Bieńkowski
* Code Documentation – Jędrzej Grabski/Maksym Bieńkowski/Aleksander Drwal
* Database Documentation - Michał Łuszczek
* Unit and Integration Tests – Aleksander Drwal/Jędrzej Grabski
