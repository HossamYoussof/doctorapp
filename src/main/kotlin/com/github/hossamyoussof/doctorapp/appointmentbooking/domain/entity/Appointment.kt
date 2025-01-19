package com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity

import java.time.LocalDateTime
import java.util.UUID

data class Appointment(
	val id: UUID = UUID.randomUUID(),
	val slotId: UUID,
	val patientId: UUID,
	val patientName: String,
	val reservedAt: LocalDateTime = LocalDateTime.now()
) {
	init {
		require(patientName.isNotBlank()) { "Patient name cannot be blank" }
	}
}