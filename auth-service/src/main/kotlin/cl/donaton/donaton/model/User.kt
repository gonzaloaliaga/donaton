// Entidad de Dominio
data class User(val id: Long, val username: String, val passwordHash: String)

// Interfaz del Repositorio
interface UserRepository {
    fun findByUsername(username: String): User?
}

// Implementación con JPA (Simulada)
@Repository
class PostgresUserRepository(private val jpaRepo: JpaUserInterface) : UserRepository {
    override fun findByUsername(username: String): User? = jpaRepo.findByUsername(username)
}