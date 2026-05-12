/* eslint-disable react/prop-types */
import { createContext, useState, useEffect } from 'react';
import profileService from '../services/profileService';

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

    const login = (userData) => {
        setUser(userData);
    };

    const logout = () => {
        setUser(null);
        setProfile(null);
        setError(null);
    };

    const updateProfile = (updatedProfile) => {
        setProfile(updatedProfile);
    };

    return (
        <AuthContext.Provider value={{ 
            user, 
            setUser, 
            profile, 
            setProfile: updateProfile,
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