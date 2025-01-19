package com.github.hossamyoussof.doctorapp.doctoravailability.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TimeSlotRepository : JpaRepository<TimeSlotEntity, UUID> {
	fun findByIsReservedFalse(): List<TimeSlotEntity>
}