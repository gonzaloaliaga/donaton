package cl.donaton.donaton.security

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

@Service
class JwtService(
    @Value("\${JWT_PRIVATE_KEY}") private val privateKeyBase64: String,
    @Value("\${JWT_PUBLIC_KEY}") private val publicKeyBase64: String
) {
    // 1. Reconstruir la Llave Privada y Pública a partir de las variables de entorno
    private val privateKey: PrivateKey = getPrivateKey()
    private val publicKey: PublicKey = getPublicKey()

    private fun getPrivateKey(): PrivateKey {
        val keyBytes = Base64.getDecoder().decode(privateKeyBase64)
        val spec = PKCS8EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(spec)
    }

    private fun getPublicKey(): PublicKey {
        val keyBytes = Base64.getDecoder().decode(publicKeyBase64)
        val spec = X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(spec)
    }

    // 2. Firmar usando la llave privada y RS256
    fun generateToken(id: Long, username: String): String {
        return Jwts.builder()
            .subject(id.toString())
            .claim("username", username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 día
            .signWith(privateKey, Jwts.SIG.RS256)
            .compact()
    }

    // Validando con la misma privateKey en este MS
    fun extractUserId(token: String): Long? {
        return try {
            Jwts.parser()
                .verifyWith(publicKey)
                .parseSignedClaims(token)
                .payload
                .subject.toLong()
        } catch (e: Exception) {
            null
        }
    }
}