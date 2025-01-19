package com.github.hossamyoussof.doctorapp.appointmentbooking.application.service

import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.`in`.BookAppointmentCommand
import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out.AppointmentPort
import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out.TimeSlotQueryPort
import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity.Appointment
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import com.github.hossamyoussof.doctorapp.notification.AppointmentNotificationService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class BookAppointmentServiceTest {

	private lateinit var appointmentPort: AppointmentPort
	private lateinit var timeSlotQueryPort: TimeSlotQueryPort
	private lateinit var notificationService: AppointmentNotificationService
	private lateinit var bookAppointmentService: BookAppointmentService

	@BeforeEach
	fun setup() {
		appointmentPort = mock()
		timeSlotQueryPort = mock()
		notificationService = mock()
		bookAppointmentService = BookAppointmentService(appointmentPort, timeSlotQueryPort, notificationService)
	}

	@Test
	fun `should successfully book appointment`() {
		// Given
		val timeSlotId = UUID.randomUUID()
		val patientId = UUID.randomUUID()
		val command = BookAppointmentCommand(
			slotId = timeSlotId,
			patientId = patientId,
			patientName = "John Doe"
		)

		val timeSlot = TimeSlot(
			id = timeSlotId,
			time = LocalDateTime.now().plusDays(1),
			doctorId = UUID.randomUUID(),
			doctorName = "Dr. Smith",
			isReserved = false,
			cost = BigDecimal("100.00")
		)

		val expectedAppointment = Appointment(
			slotId = timeSlotId,
			patientId = patientId,
			patientName = "John Doe"
		)

		whenever(timeSlotQueryPort.findTimeSlotById(timeSlotId)).thenReturn(timeSlot)
		whenever(timeSlotQueryPort.markTimeSlotAsReserved(timeSlotId)).thenReturn(true)
		whenever(appointmentPort.saveAppointment(any())).thenReturn(expectedAppointment)

		// When
		val result = bookAppointmentService.bookAppointment(command)

		// Then
		verify(timeSlotQueryPort).findTimeSlotById(timeSlotId)
		verify(timeSlotQueryPort).markTimeSlotAsReserved(timeSlotId)
		verify(appointmentPort).saveAppointment(any())
		verify(notificationService).sendConfirmationNotifications(expectedAppointment, timeSlot)
	}
}
