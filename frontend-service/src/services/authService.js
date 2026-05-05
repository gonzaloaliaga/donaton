const API_BASE_URL = 'http://localhost:8080/api/auth';

const authService = {
    // Lógica de Login
    login: async (username, password) => {
        const response = await fetch(`${API_BASE_URL}/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });
        
        if (!response.ok) throw new Error("Error de autenticación");
        return await response.json(); // Retorna el objeto AuthResponse
    },

    // Lógica de Actualización de Nombre
    updateUsername: async (currentUsername, newUsername) => {
        const response = await fetch(`${API_BASE_URL}/update-username`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ currentUsername, newUsername })
        });

        if (!response.ok) throw new Error("No se pudo actualizar el nombre");
        return await response.json();
    }
};

export default authService;