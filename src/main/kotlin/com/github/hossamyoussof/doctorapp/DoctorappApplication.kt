package com.github.hossamyoussof.doctorapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DoctorappApplication

fun main(args: Array<String>) {
	runApplication<DoctorappApplication>(*args)
}
