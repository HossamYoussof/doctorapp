# Doctor Appointment System Architecture

## Overview
The Doctor Appointment System is designed as a modular monolith, emphasizing clean architecture principles and domain-driven design. This architecture provides a balance between the simplicity of a monolithic application and the maintainability of a modular system.

## Core Modules

### Doctor Module
- Manages doctor profiles and information
- Handles doctor availability scheduling
- Maintains doctor-specific data store

### Appointment Module
- Handles appointment booking workflow
- Manages appointment confirmations
- Stores appointment-related data

### Patient Module
- Manages patient profiles and records
- Handles patient-related operations
- Maintains patient data store

### Notification Module
- Handles all system notifications
- Supports multiple channels (Email, SMS)
- Manages notification delivery and tracking

## Architectural Components

### Shared Kernel
- Domain Events: Enables loose coupling between modules
- Common Models: Shared domain models and interfaces
- Utilities: Common utility functions and helpers

### API Layer
- REST Controllers: Handle HTTP requests
- API Gateway: Entry point for all client requests

### Infrastructure
- PostgreSQL Database: Primary data store
- External Services: Third-party integrations

## Communication Patterns

### Synchronous Communication
- Direct module-to-module calls for immediate operations
- API Gateway to module communication

### Asynchronous Communication
- Domain events for cross-module notifications
- Event-based communication for loose coupling

## Design Principles
1. Modularity: Each module is self-contained with clear boundaries
2. Single Responsibility: Each module handles specific domain functionality
3. Loose Coupling: Modules communicate through well-defined interfaces and events
4. High Cohesion: Related functionality is grouped together

## Detailed Documentation

Each module has its own detailed README:
- [Appointment Booking Module](/src/main/kotlin/com/github/hossamyoussof/doctorapp/appointmentbooking/README.md)
- [Doctor Availability Module](/src/main/kotlin/com/github/hossamyoussof/doctorapp/doctoravailability/README.md)
- [Doctor Appointment Module](/src/main/kotlin/com/github/hossamyoussof/doctorapp/doctorappointment/README.md)
- [Appointment Confirmation Module](/src/main/kotlin/com/github/hossamyoussof/doctorapp/appointmentconfirmation/README.md)
- [Notification Module](/src/main/kotlin/com/github/hossamyoussof/doctorapp/notification/README.md)

## Architecture Diagram

The detailed architecture diagram is available in `architecture.puml`. View it using:
1. IDE PlantUML plugin
2. Online PlantUML viewer at https://plantuml.com/plantuml
