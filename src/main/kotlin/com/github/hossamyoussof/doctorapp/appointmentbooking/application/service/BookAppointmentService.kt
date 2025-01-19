package com.github.hossamyoussof.doctorapp.appointmentbooking.application.service

import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.`in`.BookAppointmentCommand
import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.`in`.BookAppointmentUseCase
import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out.AppointmentPort
import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out.TimeSlotQueryPort
import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity.Appointment
import com.github.hossamyoussof.doctorapp.notification.AppointmentNotificationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookAppointmentService(
	private val appointmentPort: AppointmentPort,
	private val timeSlotQueryPort: TimeSlotQueryPort,
	private val notificationService: AppointmentNotificationService
) : BookAppointmentUseCase {

	@Transactional
	override fun bookAppointment(command: BookAppointmentCommand): Appointment {
		val timeSlot = timeSlotQueryPort.findTimeSlotById(command.slotId)
			?: throw IllegalArgumentException("Time slot not found")

		if (!timeSlotQueryPort.markTimeSlotAsReserved(command.slotId)) {
			throw IllegalStateException("Time slot is already reserved")
		}

		val appointment = Appointment(
			slotId = command.slotId,
			patientId = command.patientId,
			patientName = command.patientName
		)

		val savedAppointment = appointmentPort.saveAppointment(appointment)
		
		// Send confirmation notifications
		notificationService.sendConfirmationNotifications(savedAppointment, timeSlot)
		
		return savedAppointment
	}
}