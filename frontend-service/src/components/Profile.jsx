import React, { useState, useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import authService from '../services/authService';
// CORRECCIÓN: Importaciones por defecto (sin llaves)
import AdminDashboard from './dashboards/AdminDashboard';
import DonorDashboard from './dashboards/DonorDashboard';
import VolunteerDashboard from './dashboards/VolunteerDashboard';
import LogisticDashboard from './dashboards/LogisticDashboard';

const Profile = () => {
    const { user, logout } = useContext(AuthContext);
    const [newUsername, setNewUsername] = useState('');
    const [message, setMessage] = useState('');

    const handleUpdate = async () => {
        try {
            await authService.updateUsername(user.username, newUsername);
            setMessage("Nombre actualizado con éxito.");
        } catch (err) {
            setMessage("Error al actualizar.");
        }
    };

    if (!user) return null;

    return (
        <div className="min-h-screen bg-slate-50 p-8">
            <div className="max-w-4xl mx-auto">
                {/* Cabecera de Perfil */}
                <div className="bg-white rounded-3xl shadow-sm border border-slate-100 p-8 mb-8 flex flex-col md:flex-row items-center gap-8">
                    <div className="w-24 h-24 bg-blue-600 rounded-2xl flex items-center justify-center text-4xl text-white shadow-lg shadow-blue-200 font-bold">
                        {user.username.charAt(0).toUpperCase()}
                    </div>
                    <div className="flex-1 text-center md:text-left">
                        <h1 className="text-3xl font-black text-slate-800">{user.username}</h1>
                        <p className="text-blue-600 font-semibold mt-1">Rol: {user.role}</p>
                    </div>
                    <div className="flex gap-4">
                        <button onClick={logout} className="px-6 py-3 bg-red-50 text-red-600 font-bold rounded-xl hover:bg-red-100 transition-colors">
                            Cerrar Sesión
                        </button>
                    </div>
                </div>

                {/* Sección de Configuración */}
                <div className="bg-white rounded-3xl shadow-sm border border-slate-100 p-8 mb-8">
                    <h3 className="text-xl font-bold text-slate-800 mb-6">Configuración de cuenta</h3>
                    <div className="flex flex-col sm:flex-row gap-4">
                        <input 
                            type="text" 
                            placeholder="Nuevo nombre de usuario" 
                            className="flex-1 px-4 py-3 bg-slate-50 border border-slate-200 rounded-xl focus:ring-2 focus:ring-blue-500 outline-none"
                            value={newUsername}
                            onChange={(e) => setNewUsername(e.target.value)}
                        />
                        <button onClick={handleUpdate} className="px-8 py-3 bg-slate-900 text-white font-bold rounded-xl hover:bg-slate-800 transition-all">
                            Actualizar
                        </button>
                    </div>
                    {message && <p className="mt-4 text-sm font-medium text-blue-600">{message}</p>}
                </div>

                {/* Renderizar Dashboard del Rol */}
                <div className="mt-8">
                    <h3 className="text-sm font-black text-slate-400 uppercase tracking-widest mb-6">Vista de Acceso Rápido</h3>
                    {user.role === 'ADMIN' && <AdminDashboard />}
                    {user.role === 'DONOR' && <DonorDashboard />}
                    {user.role === 'VOLUNTEER' && <VolunteerDashboard />}
                    {user.role === 'LOGISTIC' && <LogisticDashboard />}
                </div>
            </div>
        </div>
    );
};

export default Profile;