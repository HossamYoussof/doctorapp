package com.github.hossamyoussof.doctorapp.notification

import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity.Appointment
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AppointmentNotificationService {
	private val logger = LoggerFactory.getLogger(javaClass)

	fun sendConfirmationNotifications(appointment: Appointment, timeSlot: TimeSlot) {
		// Send notification to patient
		logger.info("""
			Appointment Confirmation for Patient
			----------------------------------
			Patient Name: ${appointment.patientName}
			Doctor Name: ${timeSlot.doctorName}
			Appointment Time: ${timeSlot.time}
			Cost: ${timeSlot.cost}
		""".trimIndent())

		// Send notification to doctor
		logger.info("""
			New Appointment Notification for Doctor
			-------------------------------------
			Doctor Name: ${timeSlot.doctorName}
			Patient Name: ${appointment.patientName}
			Appointment Time: ${timeSlot.time}
		""".trimIndent())
	}
}