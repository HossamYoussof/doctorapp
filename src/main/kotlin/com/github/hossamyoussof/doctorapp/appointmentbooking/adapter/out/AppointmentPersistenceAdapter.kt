package com.github.hossamyoussof.doctorapp.appointmentbooking.adapter.out

import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out.AppointmentPort
import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity.Appointment
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

// In-memory implementation for testing purposes
class AppointmentPersistenceAdapter : AppointmentPort {
	private val appointments = ConcurrentHashMap<UUID, Appointment>()

	override fun saveAppointment(appointment: Appointment): Appointment {
		appointments[appointment.id] = appointment
		return appointment
	}

	override fun findAppointmentById(id: UUID): Appointment? =
		appointments[id]

	override fun findAppointmentsByPatientId(patientId: UUID): List<Appointment> =
		appointments.values.filter { it.patientId == patientId }
}