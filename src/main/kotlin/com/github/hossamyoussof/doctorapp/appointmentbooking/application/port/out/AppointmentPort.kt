package com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out

import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity.Appointment
import java.util.UUID

interface AppointmentPort {
	fun saveAppointment(appointment: Appointment): Appointment
	fun findAppointmentById(id: UUID): Appointment?
	fun findAppointmentsByPatientId(patientId: UUID): List<Appointment>
}