# Doctor Availability Module

## Overview
The Doctor Availability module manages doctor time slots and availability scheduling following hexagonal architecture principles.

## Architecture

### Core Domain
- `TimeSlot` - Core domain entity representing a doctor's available time slot
- `TimeSlotEntity` - JPA entity for time slot persistence

### Ports
#### Input Ports
- `TimeSlotManagementUseCase` - Primary port for managing time slots
- `TimeSlotQueryUseCase` - Primary port for querying time slots

#### Output Ports
- `TimeSlotPersistencePort` - Secondary port for time slot persistence

### Adapters
#### Input Adapters
- REST Controllers for handling HTTP requests

#### Output Adapters
- `TimeSlotPersistenceAdapter` - JPA-based implementation for time slot persistence
- `TimeSlotRepository` - Spring Data repository for time slots

## Key Features
- Time slot creation and management
- Availability checking
- Time slot reservation
- Conflict prevention

## Dependencies
- Used by Appointment Booking Module