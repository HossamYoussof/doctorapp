package com.github.hossamyoussof.doctorapp.doctorappointment.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDateTime
import java.util.UUID
import kotlin.test.assertEquals

class DoctorAppointmentTest {

	@Test
	fun `should mark appointment as completed`() {
		// Given
		val appointment = DoctorAppointment(
			appointmentId = UUID.randomUUID(),
			doctorId = UUID.randomUUID(),
			patientName = "John Doe",
			appointmentTime = LocalDateTime.now(),
			status = AppointmentStatus.SCHEDULED
		)

		// When
		val completedAppointment = appointment.markAsCompleted()

		// Then
		assertAll(
			{ assertEquals(AppointmentStatus.COMPLETED, completedAppointment.status) },
			{ assertEquals(appointment.appointmentId, completedAppointment.appointmentId) },
			{ assertEquals(appointment.doctorId, completedAppointment.doctorId) },
			{ assertEquals(appointment.patientName, completedAppointment.patientName) }
		)
	}

	@Test
	fun `should cancel appointment`() {
		// Given
		val appointment = DoctorAppointment(
			appointmentId = UUID.randomUUID(),
			doctorId = UUID.randomUUID(),
			patientName = "John Doe",
			appointmentTime = LocalDateTime.now(),
			status = AppointmentStatus.SCHEDULED
		)

		// When
		val cancelledAppointment = appointment.cancel()

		// Then
		assertAll(
			{ assertEquals(AppointmentStatus.CANCELLED, cancelledAppointment.status) },
			{ assertEquals(appointment.appointmentId, cancelledAppointment.appointmentId) },
			{ assertEquals(appointment.doctorId, cancelledAppointment.doctorId) },
			{ assertEquals(appointment.patientName, cancelledAppointment.patientName) }
		)
	}
}