// Producto de la Factory
data class LoginResponse(val token: String, val username: String)

// La Factory
object AuthResponseFactory {
    fun createResponse(user: User, token: String): LoginResponse {
        return LoginResponse(
            token = token,
            username = user.username,
        )
    }
}