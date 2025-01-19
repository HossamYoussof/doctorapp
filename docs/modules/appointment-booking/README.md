# Appointment Booking Module

## Overview
The Appointment Booking module handles the patient appointment booking process following hexagonal architecture principles.

## Architecture

### Core Domain
- `Appointment` - Core domain entity representing a booked appointment
- `BookAppointmentCommand` - Input port command for booking appointments

### Ports
#### Input Ports
- `BookAppointmentUseCase` - Primary port for booking appointments

#### Output Ports
- `AppointmentPort` - Secondary port for appointment persistence
- `TimeSlotQueryPort` - Secondary port for querying time slots

### Adapters
#### Input Adapters
- REST Controllers for handling HTTP requests

#### Output Adapters
- `AppointmentPersistenceAdapter` - JPA-based implementation for appointment persistence
- `AppointmentRepository` - Spring Data repository for appointments

## Key Features
- Appointment booking validation
- Integration with Doctor Availability module
- Appointment persistence
- Notification triggering

## Dependencies
- Doctor Availability Module (for time slot management)
- Notification Module (for sending confirmations)