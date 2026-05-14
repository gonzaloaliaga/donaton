package cl.donaton.donaton.security

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

@Service
class JwtService(
    @Value("\${JWT_PUBLIC_KEY}") private val publicKeyBase64: String
) {
    // Reconstrucción de la llave
    private val publicKey: PublicKey by lazy {
        val cleanKey = publicKeyBase64.replace("\\s".toRegex(), "")
        val keyBytes = Base64.getDecoder().decode(cleanKey)
        val spec = new X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        kf.generatePublic(spec)
    }

    fun validateAndExtractId(token: String): String? {
        return try {
            // SINTAXIS PARA JJWT 0.11.5
            val claims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .body

            claims.subject
        } catch (e: Exception) {
            null
        }
    }
}