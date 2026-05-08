package cl.donaton.donaton.model

import org.springframework.stereotype.Repository

// Dominio
data class User(
    val id: Long,
    var username: String,
    val password: String,
)

// Patron Repository: Interfaz clara de acceso a datos
interface UserRepository {
    fun findByUsername(username: String): User?
}

// Implementación (Simulada para desarrollo)
@Repository
class InMemoryUserRepository : UserRepository {
    private val users = mutableListOf(
        User(1, "admin", "1111"),
        User(2, "logistic", "2222"),
        User(3, "volunteer", "3333"),
        User(4, "donor", "4444")
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