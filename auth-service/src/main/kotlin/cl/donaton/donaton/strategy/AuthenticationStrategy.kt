// Interfaz de la Estrategia
interface AuthenticationStrategy {
    fun authenticate(inputPass: String, userPass: String): Boolean
}

// Estrategia 1: Validación simple de texto plano (lo que pediste)
class SimplePasswordStrategy : AuthenticationStrategy {
    override fun authenticate(inputPass: String, userPass: String): Boolean {
        return inputPass == userPass
    }
}

// Estrategia 2: (Para el futuro) Validación con Hash/BCrypt
class SecurePasswordStrategy : AuthenticationStrategy {
    override fun authenticate(inputPass: String, userPass: String): Boolean {
        // Aquí iría la lógica de BCrypt.check()
        return false 
    }
}