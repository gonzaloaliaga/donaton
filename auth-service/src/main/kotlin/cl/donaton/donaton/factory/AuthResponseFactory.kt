package cl.donaton.donaton.factory

import cl.donaton.donaton.model.User

data class AuthResponse(
    val username: String,
    val role: String,
    val dashboardType: String, // "ADMIN_PANEL", "LOGISTIC_PANEL", "VOLUNTEER_PANEL", "DONOR_PANEL"
)

object AuthResponseFactory {
    fun createSuccessResponse(user: User): AuthResponse {
        val dashboard = when(user.role) {
            "ADMIN" -> "ADMIN_PANEL"
            "LOGISTIC" -> "LOGISTIC_PANEL"
            "VOLUNTEER" -> "VOLUNTEER_PANEL"
            else -> "DONOR_PANEL"
        }
        return AuthResponse(user.username, user.role, dashboard)
    }
}