package com.github.hossamyoussof.doctorapp.doctoravailability.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.UUID
import java.time.LocalDateTime
import java.math.BigDecimal

data class TimeSlot(
	val id: UUID = UUID.randomUUID(),
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	val time: LocalDateTime,
	val doctorId: UUID,
	val doctorName: String,
	val isReserved: Boolean = false,
	val cost: BigDecimal
)