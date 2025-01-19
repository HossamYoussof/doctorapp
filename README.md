# Doctor Appointment System

A modular doctor appointment management system demonstrating different architectural patterns.

## System Architecture

![Doctor Appointment System - Modular Monolith](out/docs/architecture/Doctor%20Appointment%20System%20-%20Modular%20Monolith.png)

The system consists of four main modules, each implementing a different architectural pattern:

1. **Doctor Availability Module** (Traditional Layered Architecture)
   - Manages doctor time slots
   - Clear separation of presentation, business, and data layers
   - Top-down dependency flow

2. **Appointment Booking Module** (Clean Architecture)
   - Handles appointment booking process
   - Domain-centric with clear boundaries
   - Uses ports and adapters for external interactions

3. **Appointment Confirmation Module** (Simple Architecture)
   - Manages appointment confirmations
   - Minimal two-class structure
   - Direct and straightforward implementation

4. **Doctor Appointment Management** (Hexagonal Architecture)
   - Handles doctor's appointment view and management
   - Core domain isolation through ports and adapters
   - Clear separation between business logic and infrastructure

## Module Integration

- Doctor Availability provides time slots to Appointment Booking
- Appointment Booking triggers Appointment Confirmation
- Doctor Appointment Management consumes confirmed appointments
- Each module maintains its architectural integrity while communicating through well-defined interfaces

## Technology Stack

- Kotlin
- Spring Boot
- JPA/Hibernate
- H2 Database

## Getting Started

1. Clone the repository
2. Run `./gradlew build`
3. Start the application with `./gradlew bootRun`
4. Access the API at `http://localhost:8080`