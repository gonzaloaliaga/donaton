@RestController
@RequestMapping("/auth")
class AuthController(private val userRepository: UserRepository) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        val user = userRepository.findByUsername(request.username)
        
        return if (user != null && checkPassword(request.password, user.passwordHash)) {
            val token = generateJWT(user) // Aquí usarías tu lógica de firma privada
            val response = AuthResponseFactory.createResponse(user, token)
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(401).body("Credenciales inválidas")
        }
    }
}