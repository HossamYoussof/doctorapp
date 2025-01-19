# Appointment Booking Module

## Overview
Handles the appointment booking process following Clean Architecture principles. This module manages the entire workflow of scheduling appointments, from initial request to confirmation.

## Clean Architecture Layers

### Domain Layer
- **Entities**
	- `Appointment`: Core domain entity
	- `BookingRequest`: Value object for booking data
- **Domain Services**
	- `AppointmentService`: Core business rules
	- `BookingValidationService`: Validation logic

### Application Layer
- **Use Cases**
	- `BookAppointmentUseCase`: Primary booking workflow
	- `CancelAppointmentUseCase`: Cancellation handling
- **Ports**
	- Input: `BookingPort`, `CancellationPort`
	- Output: `AppointmentRepository`, `NotificationPort`

### Infrastructure Layer
- **Persistence**
	- `AppointmentPersistenceAdapter`
	- `AppointmentRepository`
	- `AppointmentEntity`
- **Services**
	- `AppointmentServiceImpl`

### Interface Layer
- **Controllers**
	- `AppointmentController`: REST endpoints
- **DTOs**
	- `AppointmentRequest`
	- `AppointmentResponse`

## Project Structure
```
appointmentbooking/
├── domain/                    # Enterprise Business Rules
│   ├── entity/               # Core Business Objects
│   │   └── Appointment.kt
│   ├── model/                # Value Objects
│   │   └── BookingRequest.kt
│   └── service/              # Domain Services
│       ├── AppointmentService.kt
│       └── BookingValidationService.kt
├── application/              # Application Business Rules
│   ├── port/                # Ports (Interfaces)
│   │   ├── in/             # Input Ports
│   │   │   ├── BookingPort.kt
│   │   │   └── CancellationPort.kt
│   │   └── out/            # Output Ports
│   │       └── AppointmentRepository.kt
│   └── service/            # Use Cases
│       ├── BookAppointmentUseCase.kt
│       └── CancelAppointmentUseCase.kt
└── infrastructure/          # Interface Adapters
		├── persistence/        # Database Layer
		│   ├── AppointmentEntity.kt
		│   ├── AppointmentRepository.kt
		│   └── AppointmentPersistenceAdapter.kt
		└── web/                # Web Layer
				├── AppointmentController.kt
				└── dto/
						├── AppointmentRequest.kt
						└── AppointmentResponse.kt
```

## Key Features
- Appointment scheduling
	- Time slot selection
	- Doctor selection
	- Patient information handling
- Validation and conflict checking
	- Double booking prevention
	- Business hours validation
	- Doctor availability check
- Integration with Doctor Availability
	- Real-time slot checking
	- Schedule synchronization
- Notification triggers
	- Booking confirmations
	- Reminders
	- Status updates
- Cancellation handling
	- Cancellation policies
	- Rescheduling options
	- Notification updates

## API Endpoints
- POST /api/appointments
	- Create new appointment