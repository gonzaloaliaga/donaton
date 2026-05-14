package cl.donaton.donaton.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(@Value("\${JWT_SECRET}") private val jwtSecret: String) {
    private val secretKey = Keys.hmacShaKeyFor(jwtSecret.toByteArray())

    fun validateAndExtractId(token: String): String? {
        return try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .payload
                .subject // Extrae el ID del usuario
        } catch (e: Exception) {
            null // Token inválido o expirado
        }
    }
}