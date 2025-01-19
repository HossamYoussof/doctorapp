package com.github.hossamyoussof.doctorapp.doctorappointment.adapter.web

import com.github.hossamyoussof.doctorapp.doctorappointment.application.DoctorAppointmentService
import com.github.hossamyoussof.doctorapp.doctorappointment.domain.model.DoctorAppointment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.UUID

@RestController
@RequestMapping("/api/doctor/{doctorId}/appointments")
class DoctorAppointmentController(private val appointmentService: DoctorAppointmentService) {

	@GetMapping("/upcoming")
	fun getUpcomingAppointments(@PathVariable doctorId: UUID): ResponseEntity<List<AppointmentResponse>> =
		ResponseEntity.ok(
			appointmentService.getUpcomingAppointments(doctorId)
				.map { AppointmentResponse.from(it) }
		)

	@PutMapping("/{appointmentId}/complete")
	fun completeAppointment(
		@PathVariable doctorId: UUID,
		@PathVariable appointmentId: UUID
	): ResponseEntity<AppointmentResponse> =
		appointmentService.markAppointmentAsCompleted(appointmentId, doctorId)
			?.let { ResponseEntity.ok(AppointmentResponse.from(it)) }
			?: ResponseEntity.notFound().build()

	@PutMapping("/{appointmentId}/cancel")
	fun cancelAppointment(
		@PathVariable doctorId: UUID,
		@PathVariable appointmentId: UUID
	): ResponseEntity<AppointmentResponse> =
		appointmentService.cancelAppointment(appointmentId, doctorId)
			?.let { ResponseEntity.ok(AppointmentResponse.from(it)) }
			?: ResponseEntity.notFound().build()
}

data class AppointmentResponse(
	val appointmentId: UUID,
	val patientName: String,
	val appointmentTime: String,
	val status: String,
	val lastUpdated: String
) {
	companion object {
		fun from(appointment: DoctorAppointment) = AppointmentResponse(
			appointmentId = appointment.appointmentId,
			patientName = appointment.patientName,
			appointmentTime = appointment.appointmentTime.toString(),
			status = appointment.status.name,
			lastUpdated = appointment.lastUpdated.toString()
		)
	}
}