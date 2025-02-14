# **Flight Booking System - SkyNest**

![Flight Booking System](https://img.shields.io/badge/Status-Active-brightgreen)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen)  
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)  
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.2-orange)  

---

## **Table of Contents**
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Setup Instructions](#setup-instructions)
5. [API Endpoints](#api-endpoints)
6. [Frontend Pages](#frontend-pages)


---

## **Project Overview**
SkyNest is a **Flight Booking System** built with **Spring Boot** and **MySQL**. It allows users to search for flights, book tickets, and manage their bookings.

---

## **Features**
### **User Features**
- **Search Flights**: Search for flights by origin and destination.
- **Book Flights**: Book available seats on flights.
- **View Bookings**: View and manage personal bookings.
- **Authentication**: Secure login and registration.

---

## **Technologies Used**
- **Backend**: Spring Boot, Spring Security, JWT Authentication
- **Frontend**: Thymeleaf, Tailwind CSS
- **Database**: MySQL
- **Build Tool**: Maven
- **Other Tools**:Hibernate, Bootstrap Icon


## **Setup Instructions**
### **Prerequisites**
- Java 17 or higher
- MySQL 8.0 or higher
- Maven

### **Steps**
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/flight-booking-system.git
   cd flight-booking-system
   ```

2. **Configure MySQL**:
   - Update `application.properties` with your MySQL credentials:
     ```properties
     spring.datasource.username=your_mysql_username
     spring.datasource.password=your_mysql_password
     ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**:
   - Open your browser and navigate to:
     ```
     http://localhost:3030
     ```

---

## **API Endpoints**
### **Public Endpoints**
- `POST /api/users/signup` - User registration
- `POST /api/users/login` - User login
- `GET /api/flights` - Get all flights
- `GET /api/flights/search` - Search flights by origin and destination

### **User Endpoints**
- `GET /api/bookings/user/{userId}` - Get user bookings
- `POST /api/bookings/book` - Book a flight
- `DELETE /api/bookings/{id}` - Cancel a booking


## **Frontend Pages**
### **Public Pages**
- **Home**: `/`
- **Login**: `/login`
- **Signup**: `/signup`
- **Search Flights**: `/searchFlight`

### **User Pages**
- **Book Flight**: `/bookFlight`
- **My Bookings**: `/myBookings`






