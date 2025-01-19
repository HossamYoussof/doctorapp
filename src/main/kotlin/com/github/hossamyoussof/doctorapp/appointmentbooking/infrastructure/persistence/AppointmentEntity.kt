package com.github.hossamyoussof.doctorapp.appointmentbooking.infrastructure.persistence

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "appointments")
class AppointmentEntity(
	@Id
	@Column(columnDefinition = "VARCHAR(36)")
	val id: UUID = UUID.randomUUID(),
	
	@Column(nullable = false, columnDefinition = "VARCHAR(36)")
	val slotId: UUID,
	
	@Column(nullable = false, columnDefinition = "VARCHAR(36)")
	val patientId: UUID,
	
	@Column(nullable = false)
	val patientName: String,
	
	@Column(nullable = false)
	val reservedAt: LocalDateTime = LocalDateTime.now()
)