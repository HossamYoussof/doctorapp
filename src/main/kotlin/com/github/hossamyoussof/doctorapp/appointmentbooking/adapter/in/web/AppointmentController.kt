package com.github.hossamyoussof.doctorapp.appointmentbooking.adapter.`in`.web

import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.`in`.BookAppointmentCommand
import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.`in`.BookAppointmentUseCase
import com.github.hossamyoussof.doctorapp.appointmentbooking.application.port.out.TimeSlotQueryPort
import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.entity.Appointment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/appointments")
class AppointmentController(
	private val bookAppointmentUseCase: BookAppointmentUseCase,
	private val timeSlotQueryPort: TimeSlotQueryPort
) {
	@GetMapping("/available-slots")
	fun getAvailableSlots(): ResponseEntity<List<TimeSlotResponse>> =
		ResponseEntity.ok(
			timeSlotQueryPort.findAvailableTimeSlots()
				.map { TimeSlotResponse.from(it) }
		)

	@PostMapping
	fun bookAppointment(@RequestBody request: BookAppointmentRequest): ResponseEntity<AppointmentResponse> =
		try {
			val command = BookAppointmentCommand(
				slotId = request.slotId,
				patientId = request.patientId,
				patientName = request.patientName
			)
			val appointment = bookAppointmentUseCase.bookAppointment(command)
			ResponseEntity.ok(AppointmentResponse.from(appointment))
		} catch (e: IllegalStateException) {
			ResponseEntity.badRequest().build()
		}
}

data class BookAppointmentRequest(
	val slotId: UUID,
	val patientId: UUID,
	val patientName: String
)

data class AppointmentResponse(
	val id: UUID,
	val slotId: UUID,
	val patientId: UUID,
	val patientName: String,
	val reservedAt: String
) {
	companion object {
		fun from(appointment: Appointment) = AppointmentResponse(
			id = appointment.id,
			slotId = appointment.slotId,
			patientId = appointment.patientId,
			patientName = appointment.patientName,
			reservedAt = appointment.reservedAt.toString()
		)
	}
}

data class TimeSlotResponse(
	val id: UUID,
	val time: String,
	val doctorId: UUID,
	val doctorName: String,
	val cost: String
) {
	companion object {
		fun from(timeSlot: com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot) = TimeSlotResponse(
			id = timeSlot.id,
			time = timeSlot.time.toString(),
			doctorId = timeSlot.doctorId,
			doctorName = timeSlot.doctorName,
			cost = timeSlot.cost.toString()
		)
	}
}