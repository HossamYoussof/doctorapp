package com.github.hossamyoussof.doctorapp.doctoravailability.api

import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.service.TimeSlotService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID
import java.net.URI

@RestController
@RequestMapping("/api/doctor-availability/time-slots")
class TimeSlotController(private val timeSlotService: TimeSlotService) {

	@PostMapping
	fun createTimeSlot(@RequestBody timeSlot: TimeSlot): ResponseEntity<TimeSlot> {
		val createdTimeSlot = timeSlotService.createTimeSlot(timeSlot)
		return ResponseEntity.created(URI("/api/doctor-availability/time-slots/${createdTimeSlot.id}"))
			.body(createdTimeSlot)
	}

	@GetMapping
	fun getAllTimeSlots(): ResponseEntity<List<TimeSlot>> =
		ResponseEntity.ok(timeSlotService.getAllTimeSlots())

	@GetMapping("/{id}")
	fun getTimeSlot(@PathVariable id: UUID): ResponseEntity<TimeSlot> =
		timeSlotService.getTimeSlot(id)?.let { ResponseEntity.ok(it) }
			?: ResponseEntity.notFound().build()

	@PutMapping("/{id}")
	fun updateTimeSlot(@PathVariable id: UUID, @RequestBody updatedSlot: TimeSlot): ResponseEntity<TimeSlot> =
		timeSlotService.updateTimeSlot(id, updatedSlot)?.let { ResponseEntity.ok(it) }
			?: ResponseEntity.notFound().build()

	@DeleteMapping("/{id}")
	fun deleteTimeSlot(@PathVariable id: UUID): ResponseEntity<Unit> =
		if (timeSlotService.deleteTimeSlot(id)) ResponseEntity.ok().build()
		else ResponseEntity.notFound().build()
}