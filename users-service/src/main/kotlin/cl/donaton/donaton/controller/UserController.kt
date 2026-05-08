package cl.donaton.donaton.controller

import cl.donaton.donaton.model.User
import cl.donaton.donaton.model.InMemoryUserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository: InMemoryUserRepository) {

    /**
     * Obtiene el perfil completo del usuario por ID
     * Retorna: {id, role, email, address, run}
     */
    @GetMapping("/{userId}")
    fun getUserProfile(@PathVariable userId: Long): ResponseEntity<Any> {
        val user = userRepository.findById(userId.toString())
        
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(404).body(mapOf("message" to "Usuario no encontrado"))
        }
    }
}
