package com.github.hossamyoussof.doctorapp.doctoravailability.domain.service

import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import java.util.UUID

interface TimeSlotService {
	fun createTimeSlot(timeSlot: TimeSlot): TimeSlot
	fun getAllTimeSlots(): List<TimeSlot>
	fun getTimeSlot(id: UUID): TimeSlot?
	fun updateTimeSlot(id: UUID, updatedSlot: TimeSlot): TimeSlot?
	fun deleteTimeSlot(id: UUID): Boolean
}