package com.github.hossamyoussof.doctorapp.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@TestConfiguration
class TestConfig {
	@Bean
	@Primary
	fun testObjectMapper(): ObjectMapper {
		return ObjectMapper().apply {
			registerModule(JavaTimeModule())
			disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
		}
	}
}