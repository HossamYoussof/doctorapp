package com.github.hossamyoussof.doctorapp.doctoravailability.infrastructure.service

import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.service.TimeSlotService
import com.github.hossamyoussof.doctorapp.doctoravailability.infrastructure.persistence.TimeSlotEntity
import com.github.hossamyoussof.doctorapp.doctoravailability.infrastructure.persistence.TimeSlotRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TimeSlotServiceImpl(
	private val timeSlotRepository: TimeSlotRepository
) : TimeSlotService {

	override fun createTimeSlot(timeSlot: TimeSlot): TimeSlot {
		val entity = TimeSlotEntity(
			time = timeSlot.time,
			doctorId = timeSlot.doctorId,
			doctorName = timeSlot.doctorName,
			isReserved = timeSlot.isReserved,
			cost = timeSlot.cost
		)
		val savedEntity = timeSlotRepository.save(entity)
		return savedEntity.toTimeSlot()
	}

	override fun getAllTimeSlots(): List<TimeSlot> =
		timeSlotRepository.findAll().map { it.toTimeSlot() }

	override fun getTimeSlot(id: UUID): TimeSlot? =
		timeSlotRepository.findById(id).map { it.toTimeSlot() }.orElse(null)

	override fun updateTimeSlot(id: UUID, updatedSlot: TimeSlot): TimeSlot? {
		return timeSlotRepository.findById(id).map { existingEntity ->
			val updatedEntity = TimeSlotEntity(
				id = existingEntity.id,
				time = updatedSlot.time,
				doctorId = updatedSlot.doctorId,
				doctorName = updatedSlot.doctorName,
				isReserved = updatedSlot.isReserved,
				cost = updatedSlot.cost
			)
			timeSlotRepository.save(updatedEntity).toTimeSlot()
		}.orElse(null)
	}

	override fun deleteTimeSlot(id: UUID): Boolean {
		return if (timeSlotRepository.existsById(id)) {
			timeSlotRepository.deleteById(id)
			true
		} else false
	}

	private fun TimeSlotEntity.toTimeSlot() = TimeSlot(
		id = id,
		time = time,
		doctorId = doctorId,
		doctorName = doctorName,
		isReserved = isReserved,
		cost = cost
	)
}