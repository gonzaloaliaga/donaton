package cl.donaton.donaton.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

package cl.donaton.donaton.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtValidationFilter(private val jwtService: JwtService) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val path = request.servletPath

        // 1. Permitir el paso libre al login del Auth Service
        if (path.contains("/api/auth/login")) {
            filterChain.doFilter(request, response)
            return
        }

        // 2. Extraer el header Authorization
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token faltante o formato inválido")
            return
        }

        // 3. Validar el token
        val token = authHeader.substring(7)
        val isValid = jwtService.validateToken(token)

        if (!isValid) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado")
            return
        }

        // Si todo está bien, continuar hacia el Controller del BFF
        filterChain.doFilter(request, response)
    }
}