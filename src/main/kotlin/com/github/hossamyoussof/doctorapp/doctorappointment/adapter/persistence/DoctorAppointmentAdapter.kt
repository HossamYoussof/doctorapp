package com.github.hossamyoussof.doctorapp.doctorappointment.adapter.persistence

import com.github.hossamyoussof.doctorapp.doctorappointment.domain.model.DoctorAppointment
import com.github.hossamyoussof.doctorapp.doctorappointment.domain.port.DoctorAppointmentPort
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Component
class DoctorAppointmentAdapter : DoctorAppointmentPort {
	private val appointments = ConcurrentHashMap<UUID, DoctorAppointment>()

	override fun getDoctorAppointments(doctorId: UUID): List<DoctorAppointment> =
		appointments.values.filter { it.doctorId == doctorId }

	override fun updateAppointmentStatus(
		appointmentId: UUID,
		doctorId: UUID,
		action: (DoctorAppointment) -> DoctorAppointment
	): DoctorAppointment? {
		val appointment = appointments[appointmentId]
		if (appointment?.doctorId == doctorId) {
			val updatedAppointment = action(appointment)
			appointments[appointmentId] = updatedAppointment
			return updatedAppointment
		}
		return null
	}

	override fun findAppointment(appointmentId: UUID): DoctorAppointment? =
		appointments[appointmentId]
}