@RestController
@RequestMapping("/api/auth")
class AuthController(private val userRepository: UserRepository) {

    // Elegimos la estrategia (Podría inyectarse por configuración)
    private val authStrategy: AuthenticationStrategy = SimplePasswordStrategy()

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: Map<String, String>): ResponseEntity<AuthResponse> {
        val username = loginRequest["username"] ?: ""
        val password = loginRequest["password"] ?: ""

        val user = userRepository.findByUsername(username)

        return if (user != null && authStrategy.authenticate(password, user.password)) {
            // Usamos la Factory para la respuesta exitosa
            ResponseEntity.ok(AuthResponseFactory.createSuccessResponse(user.username))
        } else {
            // Usamos la Factory para el error
            ResponseEntity.status(401).body(AuthResponseFactory.createFailureResponse())
        }
    }
}