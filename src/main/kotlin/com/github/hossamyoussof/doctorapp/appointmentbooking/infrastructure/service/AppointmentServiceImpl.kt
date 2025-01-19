package com.github.hossamyoussof.doctorapp.appointmentbooking.infrastructure.service

import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.model.Appointment
import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.service.AppointmentService
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.service.TimeSlotService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AppointmentServiceImpl(private val timeSlotService: TimeSlotService) : AppointmentService {
	private val appointments = mutableListOf<Appointment>()

	override fun getAvailableTimeSlots(): List<TimeSlot> =
		timeSlotService.getAllTimeSlots().filter { !it.isReserved }

	override fun bookAppointment(appointment: Appointment): Appointment {
		timeSlotService.getTimeSlot(appointment.slotId)?.let { slot ->
			if (!slot.isReserved) {
				timeSlotService.updateTimeSlot(slot.id, slot.copy(isReserved = true))
				appointments.add(appointment)
				return appointment
			}
		}
		throw IllegalStateException("Time slot not available or not found")
	}

	override fun getAppointment(id: UUID): Appointment? =
		appointments.find { it.id == id }

	override fun getPatientAppointments(patientId: UUID): List<Appointment> =
		appointments.filter { it.patientId == patientId }
}