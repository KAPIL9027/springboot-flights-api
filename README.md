# Flight Status Application
This project consists of a frontend React application and a backend Spring Boot API. The application provides real-time flight status updates and allows users to subscribe to email notifications for specific flight status changes.

# Frontend (React Application)  
The React application displays all flight statuses on the homepage. Users can subscribe to receive updates on flight status changes via email.

# Features
Homepage: Displays all flights and their statuses.
Subscription: Users can enter their email addresses to subscribe to updates for specific flights. A confirmation email is sent upon subscription, and users will receive notifications for any status changes related to the subscribed flight.
Backend (Spring Boot Flights API)
The backend is a Spring Boot application that provides RESTful APIs for managing flight data and handling user subscriptions. It interacts with a PostgreSQL database to store flight and user information.

# Important Endpoints
Get All Flights

# Endpoint: GET /
Description: Retrieves all flight data from the PostgreSQL database.
Create a New Flight

# Endpoint: POST /
Description: Creates a new flight record in the flights table of the PostgreSQL database.
Subscribe to Flight Updates

# Endpoint: POST /subscribe
Description: Creates a new user subscription for flight status changes. Requires the flight number in the user data.
Other CRUD Operations

Delete Flight: Deletes a flight record by its ID or number.
Update Flight: Updates a flight record by its ID or number.
### Email Notification Service
The application includes an email service implemented using Java Mail dependency and JavaMailSender class. Users subscribed to flight updates receive email notifications when there are changes in the flight status or gate information.

### Update Notification Logic
When a flight is updated, the system checks for changes in the status or gate. If there are any changes, all users subscribed to that flight number are notified via email.

# Backend Tech Stack
Spring Boot: Framework for building the backend API.
PostgreSQL: Database for storing flight and user data.
