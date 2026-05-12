package cl.donaton.donaton.factory

import cl.donaton.donaton.model.User

/**
 * Response mínimo de autenticación.
 * El auth-service solo confirma que las credenciales son válidas.
 * Los datos adicionales (rol, dashboardType) se obtienen del servicio de usuarios.
 */
data class AuthResponse(
    val id: Long,
    val username: String,
    val token: String? = null  // Opcional: JWT o token de sesión
)

object AuthResponseFactory {
    /**
     * Crea una respuesta de autenticación exitosa.
     * Nota: Los datos de rol y dashboard se obtienen desde el servicio de usuarios.
     */
    fun createSuccessResponse(user: User): AuthResponse {
        return AuthResponse(
            id = user.id,
            username = user.username
        )
    }
}