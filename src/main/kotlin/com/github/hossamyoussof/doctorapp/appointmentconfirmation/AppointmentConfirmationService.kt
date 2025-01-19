package com.github.hossamyoussof.doctorapp.appointmentconfirmation

import org.springframework.stereotype.Service

@Service
class AppointmentConfirmationService {
	fun confirmAppointment(appointmentId: Long): Boolean {
		// Simple confirmation logic
		return true
	}

	fun getConfirmationStatus(appointmentId: Long): String {
		return "Confirmed"
	}
}