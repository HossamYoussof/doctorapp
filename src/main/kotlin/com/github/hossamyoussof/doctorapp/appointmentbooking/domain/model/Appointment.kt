package com.github.hossamyoussof.doctorapp.appointmentbooking.domain.model

import java.util.UUID
import java.time.LocalDateTime

data class Appointment(
	val id: UUID = UUID.randomUUID(),
	val slotId: UUID,
	val patientId: UUID,
	val patientName: String,
	val reservedAt: LocalDateTime = LocalDateTime.now()
)