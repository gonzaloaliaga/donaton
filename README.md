# Arquitectura del sistema
![Diagrama de Arquitectura](./docs/diagrama_sistema.png)
Realizado en draw.io

# 💻 Stack Tecnológico

## Frontend
* **React:** Biblioteca principal para la construcción de interfaces de usuario interactivas.
* **Vite:** Entorno de desarrollo ultrarrápido y empaquetador para el frontend.
* **Context API:** Herramienta nativa de React utilizada para la gestión del estado global (evitando el uso de dependencias externas pesadas).

## Backend
* **Kotlin:** Lenguaje de programación principal, elegido por su seguridad, concisión y modernidad.
* **Spring Boot:** Framework base para la creación de la API REST y la gestión de microservicios.
* **JDK 21:** Versión de soporte a largo plazo (LTS) utilizada como entorno de ejecución.

## Infraestructura y Datos
* **Docker:** Utilizado para la contenerización de los servicios, garantizando que el proyecto funcione igual en cualquier entorno.
* **Docker Compose:** Herramienta de orquestación para levantar simultáneamente el frontend, backend y bases de datos con un solo comando.
* **PostgreSQL 18:** Motor de base de datos relacional robusto asignado a cada microservicio para la persistencia de datos.

# 🏗️ Patrones de Arquitectura y Diseño
## Backend (Kotlin / Spring Boot)
El backend utiliza patrones estructurales y de comportamiento para gestionar la seguridad y la respuesta lógica del sistema de emergencias:

* **Patrón Strategy (Estrategia)**: Implementado mediante RoleBasedAuthenticationStrategy. Este patrón centraliza las reglas de validación de acceso, permitiendo aplicar políticas de seguridad diferenciadas según el rol del usuario (ADMIN, LOGISTIC, VOLUNTEER, DONOR). Facilita la futura integración de hashing (BCrypt) sin alterar el flujo del controlador.

* **Patrón Factory (Fábrica)**: Localizado en AuthResponseFactory. Se encarga de la creación lógica del objeto AuthResponse, vinculando el rol del usuario con su correspondiente "Estrategia de Dashboard" (dashboardType). Esto asegura que el backend determine la experiencia de usuario de forma centralizada.

* **Patrón Repository (Repositorio)**: Representado por la interfaz UserRepository. Actúa como una capa de abstracción entre la lógica de negocio y el origen de los datos. Esta mediación permite gestionar el acceso y la actualización de la información de forma independiente a la tecnología de persistencia utilizada, facilitando la escalabilidad y el mantenimiento del sistema.

## Frontend (React / Vite)
El frontend se basa en una arquitectura modular y reactiva:

* **Patrón Provider / Context (Contexto)**: Mediante AuthContext, se gestiona un estado global complejo que almacena la identidad, el rol y los permisos del usuario. Esto elimina el prop drilling y garantiza la integridad de la sesión en toda la plataforma.

* **Arquitectura Modular Basada en Componentes**: Se ha implementado una capa de servicios (authService.js) que encapsula toda la lógica de comunicación con la API. Los componentes no conocen detalles de red, lo que permite un mantenimiento simplificado y alta reutilización de código.

* **Strategy Mapping (UI)**: El sistema utiliza un mapeo de componentes para renderizar dashboards especializados según el rol. Esto permite que la vista de perfil (Profile) delegue la interfaz específica a módulos independientes (AdminDashboard, DonorDashboard, etc.), cumpliendo con el principio de responsabilidad única.

# 📈 Metodología de Trabajo: Git Flow
Para la gestión del código fuente, hemos adoptado el arquetipo Git Flow, organizando el desarrollo en las siguientes ramas:

* **Master**: Contiene el código de producción estable.

* **Develop**: Rama principal para la integración de nuevas funcionalidades.

* **Feature Branches**: Ramas temporales para el desarrollo de características específicas (ej. feature/auth-implementation).

**Hotfixes**: Ramas para correcciones críticas inmediatas.

# 🚀 Instalación y Ejecución
**Requisitos Previos**
* JDK 21
* Node.js (versión LTS)

**Backend**
```bash
cd auth-service
./gradlew bootRun
```

**Frontend**
```bash
cd frontend
npm install
npm run dev
```

**Nota**: Proyecto aún no testeado en Linux/macOS.