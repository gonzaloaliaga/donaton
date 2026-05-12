/* eslint-disable react/prop-types */
import { createContext, useState, useEffect } from 'react';
import profileService from '../services/profileService';
import authService from '../services/authService';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null); // Almacena datos del usuario logueado
    const [profile, setProfile] = useState(null); // Almacena datos del perfil del usuario
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    // Obtener perfil cuando el usuario se loguea
    useEffect(() => {
        if (user && user.id) {
            fetchUserProfile(user.id);
        }
    }, [user?.id]);

    const fetchUserProfile = async (userId) => {
        setLoading(true);
        setError(null);
        try {
            const profileData = await profileService.getUserProfile(userId);
            setProfile(profileData);
        } catch (err) {
            setError(err.message || "Error al cargar el perfil");
            console.error("Error al obtener perfil:", err);
        } finally {
            setLoading(false);
        }
    };

    const login = async (username, password) => {
        setLoading(true);
        setError(null);
        try {
            const userData = await authService.login(username, password);
            setUser(userData);
            return userData;
        } catch (err) {
            const mensajeError = err.message === "Failed to fetch"
                ? "Error de conexión con el servidor."
                : "Credenciales incorrectas.";
            setError(mensajeError);
            throw err;
        } finally {
            setLoading(false);
        }
    };

    const logout = () => {
        setUser(null);
        setProfile(null);
        setError(null);
    };

    const updateProfile = (updatedProfile) => {
        setProfile(updatedProfile);
    };

    const updateUsername = async (newUsername) => {
        if (!user) throw new Error('No hay usuario logueado');
        await authService.updateUsername(user.username, newUsername);
        const updatedUser = { ...user, username: newUsername };
        setUser(updatedUser);
        if (profile) {
            setProfile({ ...profile, username: newUsername });
        }
        return updatedUser;
    };

    return (
        <AuthContext.Provider value={{ 
            user, 
            setUser, 
            profile, 
            updateProfile,
            updateUsername,
            loading, 
            error, 
            login, 
            logout,
            fetchUserProfile 
        }}>
            {children}
        </AuthContext.Provider>
    );
};