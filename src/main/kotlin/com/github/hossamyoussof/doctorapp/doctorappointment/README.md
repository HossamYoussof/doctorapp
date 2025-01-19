# Doctor Appointment Management Module

This module manages doctor appointments following Hexagonal Architecture (Ports and Adapters) principles.

## Hexagonal Architecture Structure

```
doctorappointment/
├── domain/                    # Core Domain (Center)
│   ├── model/                # Domain Models
│   │   ├── DoctorAppointment.kt
│   │   └── AppointmentStatus.kt
│   └── port/                 # Ports (Interfaces)
│       ├── in/              # Primary (Driving) Ports
│       │   ├── DoctorAppointmentQueryUseCase.kt
│       │   └── AppointmentStatusUpdateUseCase.kt
│       └── out/             # Secondary (Driven) Ports
│           ├── DoctorAppointmentPersistencePort.kt
│           └── NotificationPort.kt
├── application/             # Application Services
│   └── DoctorAppointmentService.kt
└── adapter/                 # Adapters (Outside)
	├── in/                 # Primary (Driving) Adapters
	│   └── web/           # Web Controllers
	│       └── DoctorAppointmentController.kt
	└── out/               # Secondary (Driven) Adapters
		├── persistence/   # Database Adapters
		│   └── DoctorAppointmentAdapter.kt
		└── notification/  # Notification Adapters
			└── NotificationAdapter.kt
```

## Architectural Components

1. **Domain (Hexagon Center)**
   - Contains pure business logic
   - Defines ports for all external interactions
   - No dependencies on external concerns
   - Business rules and domain models

2. **Ports**
   - Primary (Driving) Ports: Define how external actors use the application
   - Secondary (Driven) Ports: Define what the application needs from external systems
   - All ports are interfaces in the domain layer

3. **Adapters (Hexagon Outside)**
   - Primary Adapters: Implement driving ports (e.g., REST controllers)
   - Secondary Adapters: Implement driven ports (e.g., persistence)
   - Convert between external and domain formats

## Key Features
- View upcoming appointments
- Mark appointments as completed
- Cancel appointments
- Status tracking with timestamps

## Endpoints
- GET /api/doctor/{doctorId}/appointments/upcoming
- PUT /api/doctor/{doctorId}/appointments/{appointmentId}/complete
- PUT /api/doctor/{doctorId}/appointments/{appointmentId}/cancel

## Benefits
- Domain logic isolation
- Easy to test and maintain
- Flexible for infrastructure changes
- Clear separation of concerns