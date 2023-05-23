# HomeSwap - API

<div style="margin-bottom: 30px;" align="center">
<img src="https://raw.githubusercontent.com/Canestin/assets/main/img/homeswap.png" alt="Logo HomeSwap"  width="330">
</div>

## Project description

This project involves the development of a web application allowing individuals to exchange their houses, apartments, lofts, etc., for short vacation periods ranging from one day to a few weeks. The idea is that the exchanges are free, but each owner can define certain services that the guests will have to provide, as well as constraints and limits to be respected.

## Main features

- Registration and authentication of members.
- Member profiles with useful information.
- Addition of new accommodations, constraints and services via a dedicated interface.
- Reservation of accommodation.
- The owner can accept, reject or request additional information.
- Detailed visualization of a dwelling (description, location, services, constraints, etc.).
- Simple and advanced search for accommodation.
- Dedicated messaging for communications between members.
- Rating system allowing users to leave ratings and comments.
- Dedicated back-office interface allowing the administrator to view the members, their announcements, and to delete elements if necessary.
- Ability to add additional features for the site administrator.
- Recommendation system (optional).

## Technologies

The backend of the application is developed using the following technologies:

- Java with Spring Boot: Java framework for developing web applications.
- JPA/Hibernate: Java library for managing data persistence and object-relational mapping.
- Database: you can use a relational database such as MySQL, PostgreSQL, or H2 (in-memory database for testing).
- Maven: project management tool for managing dependencies and building the application.

## Installation

To install and run the application locally, follow these steps:

1. Make sure you have Java JDK (version 8 or higher) and Maven installed on your machine.

2. Clone the project repository from GitHub.

3. Open a console/terminal and navigate to the project directory.

4. Edit the src/main/resources/application.properties file to configure database connection settings. For example, for MySQL:

```
spring.datasource.url=jdbc:mysql://localhost:3306/exchange_app
spring.datasource.username=root
spring.datasource.password=your_password
```

If you have an IDE, you have nothing more to do, open the project with it and launch it.

If you want to run it with Visual Studio Code:

A. Install mvn :

- For Windows, this tutorial can help you : https://www.youtube.com/watch?v=km3tLti4TCM
- For Mac OS : https://www.youtube.com/watch?v=j0OnSAP-KtU

B. Run the following command to build the application :

```
mvn clean install
```

C. Once the build is complete, run the following command to launch the application:

```
mvn spring-boot:run
```

The application will be accessible at http://localhost:8080

## API routes

Here are the main routes exposed by the API :

| Method | Route                         | Description                                      |
| ------ | ----------------------------- | ------------------------------------------------ |
| POST   | /auth/register                | Registration of a new member                     |
| POST   | /auth/login                   | Member authentication                            |
| GET    | /profile/{userId}             | Retrieve a member's profile                      |
| PUT    | /profile/{userId}             | Update a member's profile                        |
| POST   | /houses                       | Add a new accommodation                          |
| GET    | /houses/{houseId}             | Retrieve accommodation details                   |
| GET    | /houses                       | Recover all accommodations                       |
| PUT    | /houses/{houseId}             | Update listing details                           |
| DELETE | /houses/{houseId}             | Delete a slot                                    |
| POST   | /reservations                 | Create a new reservation                         |
| GET    | /reservations/{reservationId} | Retrieve booking details                         |
| GET    | /reservations/user/{userId}   | Retrieve a member's reservations                 |
| PUT    | /reservations/{reservationId} | Update booking details                           |
| DELETE | /reservations/{reservationId} | To cancel a reservation                          |
| POST   | /messages                     | Send a new message                               |
| GET    | /messages/{messageId}         | Retrieve message details                         |
| GET    | /messages/user/{userId}       | Retrieve messages from a member                  |
| GET    | /admin/users                  | Retrieve all users (for admin)                   |
| DELETE | /admin/users/{userId}         | Delete a user (for admin)                        |
| GET    | /admin/houses                 | Retrieve all housing ads                         |
| DELETE | /admin/houses/{houseId}       | Delete an accommodation ad (for the admin)       |
|
