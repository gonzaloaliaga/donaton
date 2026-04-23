package cl.donaton.donaton.factory

// El objeto que se crea
data class AuthResponse(
    val username: String,
    val status: String,
    val message: String
)

// La Factory
object AuthResponseFactory {
    fun createSuccessResponse(username: String): AuthResponse {
        return AuthResponse(username, "SUCCESS", "Bienvenido a Donaton")
    }

    fun createFailureResponse(): AuthResponse {
        return AuthResponse("unknown", "FAILURE", "Credenciales incorrectas")
    }
}