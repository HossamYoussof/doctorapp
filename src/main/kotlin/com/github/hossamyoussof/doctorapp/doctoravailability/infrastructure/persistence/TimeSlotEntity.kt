package com.github.hossamyoussof.doctorapp.doctoravailability.infrastructure.persistence

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "time_slots")
data class TimeSlotEntity(
	@Id
	@Column(columnDefinition = "VARCHAR(36)")
	val id: UUID = UUID.randomUUID(),
	
	@Column(nullable = false)
	val time: LocalDateTime,
	
	@Column(nullable = false, columnDefinition = "VARCHAR(36)")
	val doctorId: UUID,
	
	@Column(nullable = false)
	val doctorName: String,
	
	@Column(nullable = false)
	val isReserved: Boolean = false,
	
	@Column(nullable = false, precision = 10, scale = 2)
	val cost: BigDecimal
)