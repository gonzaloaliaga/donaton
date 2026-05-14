package cl.donaton.donaton.controller

import cl.donaton.donaton.factory.AuthResponseFactory
import cl.donaton.donaton.repository.UserRepository
import cl.donaton.donaton.strategy.AuthenticationStrategy
import cl.donaton.donaton.strategy.SimplePasswordStrategy
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(private val userRepository: UserRepository) {

    private val authStrategy: AuthenticationStrategy = SimplePasswordStrategy()

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: Map<String, String>): ResponseEntity<Any> {
        val username = loginRequest["username"] ?: ""
        val password = loginRequest["password"] ?: ""

        val user = userRepository.findByUsername(username)

        return if (user != null && authStrategy.authenticate(password, user)) {
            val response = AuthResponseFactory.createSuccessResponse(user)
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(401).body(mapOf("message" to "Credenciales incorrectas"))
        }
    }

    @PostMapping("/update-username")
    fun updateUsername(@RequestBody request: Map<String, String>): ResponseEntity<Any> {
        val currentUsername = request["currentUsername"] ?: ""
        val newUsername = request["newUsername"] ?: ""

        val user = userRepository.findByUsername(currentUsername)

        return if (user != null) {
            user.username = newUsername
            userRepository.save(user)
            ResponseEntity.ok(mapOf("message" to "Nombre actualizado", "username" to newUsername))
        } else {
            ResponseEntity.status(404).body(mapOf("message" to "Usuario no encontrado"))
        }
    }
}