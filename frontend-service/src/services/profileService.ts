const API_BASE_URL = 'http://localhost:8080/api/profile';

const profileService = {
    // Obtiene el perfil completo del usuario por ID
    getUserProfile: async (userId: number | string) => {
        try {
            const response = await fetch(`${API_BASE_URL}/${userId}`, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' },
            });
            
            if (!response.ok) {
                const error = await response.json().catch(() => ({ message: 'Error al obtener el perfil del usuario' }));
                throw new Error(error.message || "Error al obtener el perfil del usuario");
            }
            
            return await response.json();
        } catch (error) {
            console.error("Error en getUserProfile:", error);
            throw error;
        }
    },

    // Actualiza parcial o completamente el perfil del usuario por ID
    patchUserProfile: async (userId: number | string, profileData: any) => {
        try {
            const response = await fetch(`${API_BASE_URL}/${userId}`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(profileData),
            });
            
            if (!response.ok) {
                const error = await response.json().catch(() => ({ message: 'Error al actualizar el perfil del usuario' }));
                throw new Error(error.message || "Error al actualizar el perfil del usuario");
            }
            
            return await response.json();
        } catch (error) {
            console.error("Error en patchUserProfile:", error);
            throw error;
        }
    }
};

export default profileService;