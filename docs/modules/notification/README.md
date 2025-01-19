# Notification Module

## Overview
The Notification module handles all appointment-related notifications following hexagonal architecture principles.

## Architecture

### Core Domain
- `NotificationMessage` - Core domain entity for notification content
- `NotificationType` - Enumeration of notification types

### Ports
#### Input Ports
- `NotificationUseCase` - Primary port for sending notifications
- `NotificationQueryUseCase` - Primary port for querying notification status

#### Output Ports
- `NotificationSenderPort` - Secondary port for sending notifications
- `NotificationPersistencePort` - Secondary port for notification history

### Adapters
#### Input Adapters
- Event listeners for appointment-related events
- REST Controllers for notification status

#### Output Adapters
- `EmailNotificationAdapter` - Email notification implementation
- `NotificationRepository` - Notification history persistence

## Key Features
- Appointment confirmation notifications
- Status update notifications
- Notification history tracking
- Multiple notification channels support

## Dependencies
- Used by all other modules for notification purposes