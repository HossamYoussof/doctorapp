package com.github.hossamyoussof.doctorapp.appointmentconfirmation

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/appointment-confirmation")
class AppointmentConfirmationController(
	private val confirmationService: AppointmentConfirmationService
) {
	@PostMapping("/{appointmentId}/confirm")
	fun confirmAppointment(@PathVariable appointmentId: Long): Boolean {
		return confirmationService.confirmAppointment(appointmentId)
	}

	@GetMapping("/{appointmentId}/status")
	fun getConfirmationStatus(@PathVariable appointmentId: Long): String {
		return confirmationService.getConfirmationStatus(appointmentId)
	}
}