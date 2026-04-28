package cl.donaton.donaton.model

import org.springframework.stereotype.Repository

// Dominio
data class User(
    val id: Long,
    var username: String,
    val password: String, // Hash a futuro
    val role: String
)

// Patron Repository: Interfaz clara de acceso a datos
interface UserRepository {
    fun findByUsername(username: String): User?
}

// Implementación (Simulada para desarrollo)
@Repository
class InMemoryUserRepository : UserRepository {
    private val users = mutableListOf(
        User(1, "admin", "1111", "ADMIN"),
        User(2, "logistic", "2222", "LOGISTIC"),
        User(3, "volunteer", "3333", "VOLUNTEER"),
        User(4, "donor", "4444", "DONOR")
    )
    override fun findByUsername(username: String) = users.find { it.username == username }

    fun update(username: String, newUsername: String): User? {
        val user = findByUsername(username)
        user?.let {
            it.username = newUsername
        }
        return user
    }
}