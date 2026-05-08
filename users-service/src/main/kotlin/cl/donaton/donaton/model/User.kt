package cl.donaton.donaton.model

import org.springframework.stereotype.Repository

// Dominio
data class User(
    val id: Long,
    val role: String,
    val email: String,
    val address: String,
    val run: String
)

// Patron Repository: Interfaz clara de acceso a datos
interface UserRepository {
    fun findById(id: String): User?
}

// Implementación (Simulada para desarrollo)
@Repository
class InMemoryUserRepository : UserRepository {
    private val users = mutableListOf(
        User(1, "ADMIN", "admin@donaton.cl", "Calle Con Asfalto 123", "220330591"),
        User(2, "LOGISTIC", "logistic@donaton.cl", "Calle Sin Asfalto 123", "218854608"),
        User(3, "VOLUNTEER", "volunteer@gmail.com", "Depa Ruidoso 456", "220648152"),
        User(4, "DONOR", "donor@gmail.cl", "Depa Ruidoso 456", "123456789"),
    )
    override fun findById(id: Long) = users.find { it.id == id.toLongOrNull() }
}