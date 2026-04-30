package cl.donaton.donaton.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**") // Aplica a todos los endpoints (incluido /api/auth/**)
                    .allowedOrigins(
                        "http://localhost", 
                        "http://127.0.0.1"  // Añadir 127.0.0.1 por si Vite usa esa IP
                    )
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // OPTIONS es vital aquí
                    .allowedHeaders("*")
                    .allowCredentials(true)
            }
        }
    }
}