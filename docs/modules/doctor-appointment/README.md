# Doctor Appointment Module

## Overview
The Doctor Appointment module manages the doctor's view and interaction with appointments following hexagonal architecture principles.

## Architecture

### Core Domain
- `DoctorAppointment` - Core domain entity representing a doctor's appointment view
- `AppointmentStatus` - Enumeration of possible appointment statuses

### Ports
#### Input Ports
- `DoctorAppointmentQueryUseCase` - Primary port for querying appointments
- `AppointmentStatusUpdateUseCase` - Primary port for updating appointment status

#### Output Ports
- `DoctorAppointmentPersistencePort` - Secondary port for appointment persistence
- `NotificationPort` - Secondary port for sending status update notifications

### Adapters
#### Input Adapters
- REST Controllers for handling HTTP requests

#### Output Adapters
- `DoctorAppointmentPersistenceAdapter` - JPA-based implementation
- `NotificationAdapter` - Integration with Notification module

## Key Features
- View upcoming appointments
- Appointment history tracking
- Status updates (completed, cancelled, etc.)
- Integration with notifications

## Dependencies
- Appointment Booking Module (for appointment data)
- Notification Module (for status updates)