package com.github.hossamyoussof.doctorapp.appointmentbooking.infrastructure.persistence

import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out.AppointmentPort
import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity.Appointment
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class AppointmentPersistenceAdapter(
	private val appointmentRepository: AppointmentRepository
) : AppointmentPort {

	override fun saveAppointment(appointment: Appointment): Appointment {
		val entity = AppointmentEntity(
			id = appointment.id,
			slotId = appointment.slotId,
			patientId = appointment.patientId,
			patientName = appointment.patientName,
			reservedAt = appointment.reservedAt
		)
		val savedEntity = appointmentRepository.save(entity)
		return savedEntity.toDomain()
	}

	override fun findAppointmentById(id: UUID): Appointment? =
		appointmentRepository.findById(id).map { it.toDomain() }.orElse(null)

	override fun findAppointmentsByPatientId(patientId: UUID): List<Appointment> =
		appointmentRepository.findByPatientId(patientId).map { it.toDomain() }

	private fun AppointmentEntity.toDomain() = Appointment(
		id = id,
		slotId = slotId,
		patientId = patientId,
		patientName = patientName,
		reservedAt = reservedAt
	)
}