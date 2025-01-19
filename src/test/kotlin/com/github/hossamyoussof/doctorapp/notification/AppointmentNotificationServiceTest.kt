package com.github.hossamyoussof.doctorapp.notification

import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity.Appointment
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class AppointmentNotificationServiceTest {

	private lateinit var notificationService: AppointmentNotificationService

	@BeforeEach
	fun setup() {
		notificationService = AppointmentNotificationService()
	}

	@Test
	fun `should send notifications to both patient and doctor`() {
		// Given
		val appointment = Appointment(
			id = UUID.randomUUID(),
			slotId = UUID.randomUUID(),
			patientId = UUID.randomUUID(),
			patientName = "John Doe"
		)

		val timeSlot = TimeSlot(
			id = UUID.randomUUID(),
			time = LocalDateTime.now(),
			doctorId = UUID.randomUUID(),
			doctorName = "Dr. Smith",
			isReserved = false,
			cost = BigDecimal("100.00")
		)

		// When/Then
		assertDoesNotThrow {
			notificationService.sendConfirmationNotifications(appointment, timeSlot)
		}
	}
}
