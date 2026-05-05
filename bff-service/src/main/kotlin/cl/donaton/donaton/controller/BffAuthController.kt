package cl.donaton.donaton.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = ["http://localhost:5173"]) // Frontend (Vite)
class BffAuthController {

    // Herramienta de Spring para hacer peticiones HTTP a otros servicios
    private val restTemplate = RestTemplate()

    // Rescata la URL del auth-service que definimos en el application.properties
    @Value("\${services.auth.url}")
    lateinit var authServiceUrl: String

    @PostMapping("/login")
    fun login(@RequestBody credentials: Any): ResponseEntity<Any> {
        val targetUrl = "$authServiceUrl/api/auth/login"
        
        // El BFF actúa de puente: recibe las credenciales del front, se las envía al auth-service, 
        // y devuelve la respuesta del auth-service al frontend.
        return restTemplate.postForEntity(targetUrl, credentials, Any::class.java)
    }
}