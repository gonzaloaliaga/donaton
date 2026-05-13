package cl.donaton.donaton.model

import org.springframework.stereotype.Repository

// Dominio
data class Profile(
    val id: Long,
    val role: String,
    val email: String,
    val address: String,
    val run: String
)

// Patron Repository: Interfaz clara de acceso a datos
interface ProfileRepository {
    fun findById(id: Long): Profile?
    fun save(profile: Profile): Profile
}

// Implementación (Simulada para desarrollo)
@Repository
class InMemoryProfileRepository : ProfileRepository {
    private val profiles = mutableListOf(
        Profile(1, "ADMIN", "admin@donaton.cl", "Calle Con Asfalto 123", "220330591"),
        Profile(2, "LOGISTIC", "logistic@donaton.cl", "Calle Sin Asfalto 123", "218854608"),
        Profile(3, "VOLUNTEER", "volunteer@gmail.com", "Depa Ruidoso 456", "220648152"),
        Profile(4, "DONOR", "donor@gmail.cl", "Depa Ruidoso 456", "123456789"),
    )
    override fun findById(id: Long) = profiles.find { it.id == id }

    override fun save(profile: Profile): Profile {
        val index = profiles.indexOfFirst { it.id == profile.id }
        if (index != -1) {
            profiles[index] = profile
        } else {
            profiles.add(profile)
        }
        return profile
    }
}