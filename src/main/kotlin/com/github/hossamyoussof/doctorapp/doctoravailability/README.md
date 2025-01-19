# Doctor Availability Module

## Overview
Manages doctor availability and time slot scheduling. This module is responsible for maintaining and querying doctor schedules, available time slots, and managing booking windows.

## Architecture
This module follows Traditional Layered Architecture with clear separation of concerns:

```
doctoravailability/
├── api/                 # Presentation Layer
│   └── TimeSlotController.kt
├── domain/             # Business Layer
│   ├── model/          # Domain Models
│   │   └── TimeSlot.kt
│   └── service/        # Business Services
│       └── TimeSlotService.kt
└── infrastructure/     # Data Access Layer
	├── persistence/    # Data Storage
	│   ├── TimeSlotEntity.kt
	│   ├── TimeSlotRepository.kt
	│   └── TimeSlotPersistenceAdapter.kt
	└── service/        # Service Implementations
		└── TimeSlotServiceImpl.kt
```

## Components

### Domain Model
- `TimeSlot`: Represents a bookable time period for a doctor
  - Start and end time
  - Status (available, booked, blocked)
  - Associated doctor ID
- `Doctor`: Contains doctor availability preferences and schedule
  - Working hours
  - Break periods
  - Booking constraints

### Services
- `TimeSlotService`: Core service managing time slot operations
  - Time slot creation and validation
  - Availability checking
  - Schedule management
- `TimeSlotServiceImpl`: Implementation of time slot business logic
  - Business rules enforcement
  - Conflict prevention logic
  - Booking window management

### Infrastructure
- `TimeSlotRepository`: Data access for time slots
- `TimeSlotEntity`: Database entity for time slot persistence
- `TimeSlotPersistenceAdapter`: Adapter for data persistence operations

### API Endpoints
- POST /api/doctor-availability/time-slots
- GET /api/doctor-availability/time-slots
- GET /api/doctor-availability/time-slots/{id}
- PUT /api/doctor-availability/time-slots/{id}
- DELETE /api/doctor-availability/time-slots/{id}

## Key Features
- Time slot creation and management
  - Create single or recurring slots
  - Modify existing slots
  - Delete unused slots
- Availability checking
  - Real-time slot status
  - Conflict detection
- Schedule conflict prevention
  - Overlapping appointment prevention
  - Buffer time management
- Booking window management
  - Advance booking limits
  - Last-minute booking rules

## Dependencies
- Appointment Module: For booking validation
- Shared Kernel: For common models and events

## Layer Responsibilities

1. **Presentation Layer (API)**
   - Handles HTTP requests and responses
   - Input validation and response formatting
   - Routes requests to appropriate business services

2. **Business Layer (Domain)**
   - Contains core business logic
   - Defines service interfaces
   - Houses domain models and business rules

3. **Data Access Layer (Infrastructure)**
   - Implements data persistence
   - Manages database interactions
   - Handles external service communications