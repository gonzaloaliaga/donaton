# 🏗️ Patrones de Arquitectura y Diseño
## Backend (Kotlin / Spring Boot)
El backend implementa patrones de diseño del "Gang of Four" para asegurar la escalabilidad y el desacoplamiento del código:

**Patrón Strategy (Estrategia)**: Se utiliza para gestionar diferentes métodos de autenticación. Actualmente implementa una SimplePasswordStrategy para validación de texto plano, dejando la estructura lista para integrar SecurePasswordStrategy con encriptación en el futuro.

**Patrón Factory (Fábrica)**: Implementado en AuthResponseFactory, este patrón se encarga de centralizar la creación de objetos de respuesta (AuthResponse), permitiendo que el controlador no se preocupe por la construcción lógica de los mensajes de éxito o error.

**Patrón Repository (Repositorio)**: Utilizado para mediar entre el dominio del usuario y la persistencia de datos. La interfaz UserRepository permite gestionar el acceso a los datos de forma independiente a la tecnología de base de datos utilizada.

## Frontend (React / Vite)
El frontend se basa en una arquitectura modular y reactiva:

**Patrón Provider / Context (Contexto)**: Implementado mediante AuthContext, este patrón permite gestionar el estado de autenticación de forma global. Cualquier componente puede acceder a la información del usuario logueado sin necesidad de pasar "props" manualmente.

**Arquitectura Modular Basada en Componentes**: La aplicación está dividida en módulos independientes y reutilizables como Login y Profile, lo que facilita el mantenimiento y la expansión de la interfaz.

# 📈 Metodología de Trabajo: Git Flow
Para la gestión del código fuente, hemos adoptado el arquetipo Git Flow, organizando el desarrollo en las siguientes ramas:

**Master**: Contiene el código de producción estable.

**Develop**: Rama principal para la integración de nuevas funcionalidades.

**Feature Branches**: Ramas temporales para el desarrollo de características específicas (ej. feature/auth-implementation).

**Hotfixes**: Ramas para correcciones críticas inmediatas.

# 🚀 Instalación y Ejecución
**Requisitos Previos**
- JDK 21
- Node.js (versión LTS)

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

Aún no testeado en Linux/macOS.