package com.github.hossamyoussof.doctorapp.appointmentbooking.domain.service

import com.github.hossamyoussof.doctorapp.appointmentbooking.domain.model.Appointment
import com.github.hossamyoussof.doctorapp.doctoravailability.domain.model.TimeSlot
import java.util.UUID

interface AppointmentService {
	fun getAvailableTimeSlots(): List<TimeSlot>
	fun bookAppointment(appointment: Appointment): Appointment
	fun getAppointment(id: UUID): Appointment?
	fun getPatientAppointments(patientId: UUID): List<Appointment>
}