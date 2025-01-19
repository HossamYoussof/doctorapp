package com.github.hossamyoussof.doctorapp.doctorappointment.application

import com.github.hossamyoussof.doctorapp.doctorappointment.domain.model.DoctorAppointment
import com.github.hossamyoussof.doctorapp.doctorappointment.domain.port.DoctorAppointmentPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DoctorAppointmentService(private val appointmentPort: DoctorAppointmentPort) {

	fun getUpcomingAppointments(doctorId: UUID): List<DoctorAppointment> =
		appointmentPort.getDoctorAppointments(doctorId)
			.filter { it.appointmentTime > java.time.LocalDateTime.now() }
			.sortedBy { it.appointmentTime }

	fun markAppointmentAsCompleted(appointmentId: UUID, doctorId: UUID): DoctorAppointment? =
		appointmentPort.updateAppointmentStatus(appointmentId, doctorId) { it.markAsCompleted() }

	fun cancelAppointment(appointmentId: UUID, doctorId: UUID): DoctorAppointment? =
		appointmentPort.updateAppointmentStatus(appointmentId, doctorId) { it.cancel() }
}