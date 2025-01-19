package com.github.hossamyoussof.doctorapp.doctoravailability.infrastructure.persistence

import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out.TimeSlotQueryPort
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.util.UUID

@Primary
@Component
class TimeSlotPersistenceAdapter(
	private val timeSlotRepository: TimeSlotRepository
) : TimeSlotQueryPort {

	override fun findAvailableTimeSlots(): List<TimeSlot> =
		timeSlotRepository.findByIsReservedFalse().map { it.toDomain() }

	override fun findTimeSlotById(id: UUID): TimeSlot? =
		timeSlotRepository.findById(id).map { it.toDomain() }.orElse(null)

	override fun markTimeSlotAsReserved(id: UUID): Boolean {
		return timeSlotRepository.findById(id).map { entity ->
			val updatedEntity = entity.copy(isReserved = true)
			timeSlotRepository.save(updatedEntity)
			true
		}.orElse(false)
	}

	private fun TimeSlotEntity.toDomain() = TimeSlot(
		id = id,
		time = time,
		doctorId = doctorId,
		doctorName = doctorName,
		isReserved = isReserved,
		cost = cost
	)
}