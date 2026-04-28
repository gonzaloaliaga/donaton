package cl.donaton.donaton.strategy

import cl.donaton.donaton.model.User

interface AuthenticationStrategy {
    // Recibe el objeto User completo para validar según sus atributos
    fun authenticate(inputPass: String, user: User): Boolean
}

class RoleBasedAuthenticationStrategy : AuthenticationStrategy {
    override fun authenticate(inputPass: String, user: User): Boolean {
        val isPasswordCorrect = inputPass == user.password
        
        // Lógica de estrategia según rol:
        return when (user.role) {
            "ADMIN" -> isPasswordCorrect && inputPass.length >= 4 // Los admin deben tener claves de min 4
            "LOGISTIC" -> isPasswordCorrect // Logística validación estándar
            "VOLUNTEER" -> isPasswordCorrect // Voluntarios validación estándar
            "DONOR" -> isPasswordCorrect // Donantes validación estándar
            else -> false
        }
    }
}

// Se mantiene para volver a usarla en pruebas
class SimplePasswordStrategy : AuthenticationStrategy {
    override fun authenticate(inputPass: String, user: User): Boolean {
        return inputPass == user.password
    }
}