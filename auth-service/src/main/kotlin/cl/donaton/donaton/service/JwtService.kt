package cl.donaton.donaton.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    @Value("\${JWT_SECRET}") private val jwtSecret: String
) {
    private val secretKey = Keys.hmacShaKeyFor(jwtSecret.toByteArray())

    fun generateToken(userId: Long, username: String): String {
        return Jwts.builder()
            .subject(userId.toString())
            .claim("username", username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 3600000)) // 1 hora de validez
            .signWith(secretKey)
            .compact()
    }

    fun extractUserId(token: String): String? {
    return try {
        Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    } catch (e: Exception) {
        null
    }
}
}