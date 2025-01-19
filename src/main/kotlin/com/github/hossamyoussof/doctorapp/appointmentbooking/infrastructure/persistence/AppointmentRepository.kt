package com.github.hossamyoussof.doctorapp.appointmentbooking.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AppointmentRepository : JpaRepository<AppointmentEntity, UUID> {
	fun findByPatientId(patientId: UUID): List<AppointmentEntity>
}