package cl.donaton.donaton.strategy

import cl.donaton.donaton.model.User

/**
 * Estrategia de autenticación.
 * El auth-service SOLO valida credenciales (username + password).
 * La lógica de roles y asignación de dashboards se maneja en el servicio de usuarios.
 */
interface AuthenticationStrategy {
    fun authenticate(inputPass: String, user: User): Boolean
}

/**
 * Estrategia simple: validación básica de contraseña
 */
class SimplePasswordStrategy : AuthenticationStrategy {
    override fun authenticate(inputPass: String, user: User): Boolean {
        return inputPass == user.password
    }
}

/**
 * Estrategia con validaciones adicionales de seguridad (ej: longitud mínima)
 */
class EnhancedSecurityStrategy : AuthenticationStrategy {
    override fun authenticate(inputPass: String, user: User): Boolean {
        val isPasswordCorrect = inputPass == user.password
        val hasMinimumLength = inputPass.length >= 4
        return isPasswordCorrect && hasMinimumLength
    }
}