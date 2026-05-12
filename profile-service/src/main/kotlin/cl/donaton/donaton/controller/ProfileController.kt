package cl.donaton.donaton.controller

import cl.donaton.donaton.model.Profile
import cl.donaton.donaton.model.InMemoryProfileRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profile")
class ProfileController(private val profileRepository: InMemoryProfileRepository) {

    /**
     * Obtiene el perfil completo del usuario por ID
     * Retorna: {id, role, email, address, run}
     */
    @GetMapping("/{userId}")
    fun getUserProfile(@PathVariable userId: Long): ResponseEntity<Any> {
        val profile = profileRepository.findById(userId)
        
        return if (profile != null) {
            ResponseEntity.ok(profile)
        } else {
            ResponseEntity.status(404).body(mapOf("message" to "Usuario no encontrado"))
        }
    }
}
