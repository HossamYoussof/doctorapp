package com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.`in`

import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity.Appointment
import java.util.UUID

interface BookAppointmentUseCase {
	fun bookAppointment(command: BookAppointmentCommand): Appointment
}

data class BookAppointmentCommand(
	val slotId: UUID,
	val patientId: UUID,
	val patientName: String
) {
	init {
		require(patientName.isNotBlank()) { "Patient name cannot be blank" }
	}
}