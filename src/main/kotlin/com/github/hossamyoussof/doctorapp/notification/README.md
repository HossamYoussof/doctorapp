# Appointment Notification Module

A simple notification module that handles sending confirmation notifications for appointments.

## Structure

```
notification/
└── AppointmentNotificationService.kt
```

## Functionality

The module provides a simple notification service that:
- Sends appointment confirmation to patients
- Sends appointment notifications to doctors
- Uses logging to simulate notification delivery

## Integration

Integrated with the Appointment Booking module through:
- Direct service injection in BookAppointmentService
- Automatic notification sending after successful appointment booking

## Notification Content

1. Patient Notification includes:
   - Patient Name
   - Doctor Name
   - Appointment Time
   - Cost

2. Doctor Notification includes:
   - Doctor Name
   - Patient Name
   - Appointment Time