package com.github.hossamyoussof.doctorapp.doctorappointment.domain.port

import com.github.hossamyoussof.doctorapp.doctorappointment.domain.model.DoctorAppointment
import java.util.UUID

interface DoctorAppointmentPort {
	fun getDoctorAppointments(doctorId: UUID): List<DoctorAppointment>
	fun updateAppointmentStatus(appointmentId: UUID, doctorId: UUID, action: (DoctorAppointment) -> DoctorAppointment): DoctorAppointment?
	fun findAppointment(appointmentId: UUID): DoctorAppointment?
}