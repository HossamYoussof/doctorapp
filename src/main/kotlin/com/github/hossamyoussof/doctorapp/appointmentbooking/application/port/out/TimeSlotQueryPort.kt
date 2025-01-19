package com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out

import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import java.util.UUID

interface TimeSlotQueryPort {
	fun findAvailableTimeSlots(): List<TimeSlot>
	fun findTimeSlotById(id: UUID): TimeSlot?
	fun markTimeSlotAsReserved(id: UUID): Boolean
}