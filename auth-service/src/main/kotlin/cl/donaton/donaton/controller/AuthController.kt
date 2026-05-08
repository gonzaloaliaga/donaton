package cl.donaton.donaton.controller

import cl.donaton.donaton.factory.AuthResponse
import cl.donaton.donaton.factory.AuthResponseFactory
import cl.donaton.donaton.model.InMemoryUserRepository
import cl.donaton.donaton.model.UserRepository
import cl.donaton.donaton.strategy.AuthenticationStrategy
import cl.donaton.donaton.strategy.RoleBasedAuthenticationStrategy
import cl.donaton.donaton.strategy.SimplePasswordStrategy
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin

@RestController
@RequestMapping("/api/auth")
class AuthController(private val userRepository: UserRepository) {

    // Estrategia configurable: por defecto simple, pero puede ser extendida
    private val authStrategy: AuthenticationStrategy = SimplePasswordStrategy()

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: Map<String, String>): ResponseEntity<Any> {
        val username = loginRequest["username"] ?: ""
        val password = loginRequest["password"] ?: ""

        val user = userRepository.findByUsername(username)

        return if (user != null && authStrategy.authenticate(password, user)) {
            // ÉXITO: Devolvemos datos mínimos, el cliente obtiene rol del users-service
            val response = AuthResponseFactory.createSuccessResponse(user)
            ResponseEntity.ok(response)
        } else {
            // ERROR:
            ResponseEntity.status(401).body(mapOf("message" to "Credenciales incorrectas"))
        }
    }

    @PostMapping("/update-username")
    fun updateUsername(@RequestBody request: Map<String, String>): ResponseEntity<Any> {
        val currentUsername = request["currentUsername"] ?: ""
        val newUsername = request["newUsername"] ?: ""

        // Cast manual para acceder al metodo del repositorio de memoria
        val updatedUser = (userRepository as InMemoryUserRepository).update(currentUsername, newUsername)

        return if (updatedUser != null) {
            ResponseEntity.ok(mapOf("message" to "Nombre actualizado", "username" to newUsername))
        } else {
            ResponseEntity.status(404).body(mapOf("message" to "Usuario no encontrado"))
        }
    }
}