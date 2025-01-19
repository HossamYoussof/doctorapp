package com.github.hossamyoussof.doctorapp.appointmentbooking.adapter.out

import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out.TimeSlotQueryPort
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.service.TimeSlotService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class TimeSlotAdapter(private val timeSlotService: TimeSlotService) : TimeSlotQueryPort {
	
	override fun findAvailableTimeSlots(): List<TimeSlot> =
		timeSlotService.getAllTimeSlots().filter { !it.isReserved }

	override fun findTimeSlotById(id: UUID): TimeSlot? =
		timeSlotService.getTimeSlot(id)

	override fun markTimeSlotAsReserved(id: UUID): Boolean {
		val timeSlot = timeSlotService.getTimeSlot(id) ?: return false
		if (timeSlot.isReserved) return false
		
		return timeSlotService.updateTimeSlot(id, timeSlot.copy(isReserved = true)) != null
	}
}