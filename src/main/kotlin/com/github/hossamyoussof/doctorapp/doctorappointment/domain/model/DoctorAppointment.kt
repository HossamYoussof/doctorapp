package com.github.hossamyoussof.doctorapp.doctorappointment.domain.model

import java.time.LocalDateTime
import java.util.UUID

enum class AppointmentStatus {
	SCHEDULED,
	COMPLETED,
	CANCELLED
}

data class DoctorAppointment(
	val appointmentId: UUID,
	val doctorId: UUID,
	val patientName: String,
	val appointmentTime: LocalDateTime,
	val status: AppointmentStatus,
	val lastUpdated: LocalDateTime = LocalDateTime.now()
) {
	fun markAsCompleted(): DoctorAppointment =
		copy(status = AppointmentStatus.COMPLETED, lastUpdated = LocalDateTime.now())

	fun cancel(): DoctorAppointment =
		copy(status = AppointmentStatus.CANCELLED, lastUpdated = LocalDateTime.now())
}