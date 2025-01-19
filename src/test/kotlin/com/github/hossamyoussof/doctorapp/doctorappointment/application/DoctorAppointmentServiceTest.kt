package com.github.hossamyoussof.doctorapp.doctorappointment.application

import com.github.hossamyoussof.doctorapp.doctorappointment.domain.model.AppointmentStatus
import com.github.hossamyoussof.doctorapp.doctorappointment.domain.model.DoctorAppointment
import com.github.hossamyoussof.doctorapp.doctorappointment.domain.port.DoctorAppointmentPort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.ArgumentMatchers.any
import java.time.LocalDateTime
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DoctorAppointmentServiceTest {

	private lateinit var appointmentPort: DoctorAppointmentPort
	private lateinit var appointmentService: DoctorAppointmentService

	@BeforeEach
	fun setup() {
		appointmentPort = mock(DoctorAppointmentPort::class.java)
		appointmentService = DoctorAppointmentService(appointmentPort)
	}

	@Test
	fun `should get upcoming appointments for doctor`() {
		// Given
		val doctorId = UUID.randomUUID()
		val now = LocalDateTime.now()
		val appointments = listOf(
			createAppointment(doctorId, now.plusHours(1)),
			createAppointment(doctorId, now.plusHours(2)),
			createAppointment(doctorId, now.minusHours(1))
		)

		`when`(appointmentPort.getDoctorAppointments(doctorId)).thenReturn(appointments)

		// When
		val upcomingAppointments = appointmentService.getUpcomingAppointments(doctorId)

		// Then
		assertEquals(2, upcomingAppointments.size)
		assertTrue(upcomingAppointments.all { it.appointmentTime > now })
		verify(appointmentPort).getDoctorAppointments(doctorId)
	}

	private fun createAppointment(doctorId: UUID, time: LocalDateTime) = DoctorAppointment(
		appointmentId = UUID.randomUUID(),
		doctorId = doctorId,
		patientName = "Test Patient",
		appointmentTime = time,
		status = AppointmentStatus.SCHEDULED
	)

}