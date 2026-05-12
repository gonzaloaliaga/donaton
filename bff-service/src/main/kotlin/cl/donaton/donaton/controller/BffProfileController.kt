package cl.donaton.donaton.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = ["http://localhost:5173"])
class BffProfileController {

    private val restTemplate = RestTemplate()
    private val objectMapper = ObjectMapper()

    @Value("\${services.profile.url}")
    lateinit var profileServiceUrl: String

    @GetMapping("/{userId}")
    fun getUserProfile(@PathVariable userId: Long): ResponseEntity<Any> {
        val targetUrl = "$profileServiceUrl/api/users/$userId"
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        val request = HttpEntity("", headers)

        return try {
            val response = restTemplate.exchange(targetUrl, HttpMethod.GET, request, Any::class.java)
            ResponseEntity.status(response.statusCode).body(response.body)
        } catch (ex: HttpStatusCodeException) {
            val errorBody = try {
                objectMapper.readValue(ex.responseBodyAsString, Any::class.java)
            } catch (_: Exception) {
                mapOf("message" to ex.statusText)
            }
            ResponseEntity.status(ex.statusCode).body(errorBody)
        }
    }
}