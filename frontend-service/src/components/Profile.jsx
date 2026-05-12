import { useContext, useState } from 'react';
import { AuthContext } from '../context/AuthContext';
import MainLayout from './MainLayout';

// DASHBOARDS
import { AdminDashboard } from './dashboards/AdminDashboard';
import { LogisticDashboard } from './dashboards/LogisticDashboard';
import { VolunteerDashboard } from './dashboards/VolunteerDashboard';
import { DonorDashboard } from './dashboards/DonorDashboard';

// MAPEO DE ESTRATEGIAS
const ROLE_STRATEGIES = {
    ADMIN: <AdminDashboard />,
    LOGISTIC: <LogisticDashboard />,
    VOLUNTEER: <VolunteerDashboard />,
    DONOR: <DonorDashboard />
};

export default function Profile() {
    const { user, profile, updateUsername, loading, error, logout } = useContext(AuthContext);
    const [newUsername, setNewUsername] = useState('');
    const [message, setMessage] = useState('');

    const handleUpdateName = async () => {
        if (!newUsername.trim()) {
            setMessage('El nombre no puede estar vacío.');
            return;
        }

        setMessage('');

        try {
            await updateUsername(newUsername);
            alert("¡Nombre actualizado!");
            setNewUsername('');
        } catch (error) {
            console.error("Error:", error);
        }
    };

    if (!user) return null;
    if (!profile) return null;

    if (loading) return (
        <div className="min-h-screen bg-slate-50 p-8 flex items-center justify-center">
            <p className="text-lg text-slate-600">Cargando perfil...</p>
        </div>
    );

    if (error) return (
        <div className="min-h-screen bg-slate-50 p-8 flex items-center justify-center">
            <p className="text-lg text-red-600">Error al cargar perfil: {error}</p>
        </div>
    );

    return (
        <MainLayout>
            {/* 1. Header y Avatar*/}
            <div className="bg-white p-6 rounded-2xl shadow-sm border border-slate-200 mb-6 flex justify-between items-center">
                <div className="flex items-center gap-4">
                    <div>
                        <h1 className="text-2xl font-bold text-slate-800">Bienvenido {user.username}</h1>
                        <p className="text-slate-500 font-medium text-sm">Perfil: {profile.role}</p>
                    </div>
                </div>
            </div>

            {/* 2. Dashboard según mapeo (Tu misma lógica) */}
            <div className="mb-6">
                {ROLE_STRATEGIES[profile.role] || (
                    <div className="p-4 bg-yellow-50 text-yellow-800 rounded-lg border border-yellow-200">
                        Rol no reconocido: {profile.role}
                    </div>
                )}
            </div>

            {/* 3. Footer para actualizar nombre */}
            <div className="bg-white p-6 rounded-2xl shadow-sm border border-slate-200">
                <h3 className="text-sm font-bold text-slate-700 mb-3 uppercase tracking-wider">Ajustes de Perfil</h3>
                <div className="flex items-center gap-4">
                    <input 
                        type="text"
                        value={newUsername} 
                        onChange={(e) => {
                            setNewUsername(e.target.value);
                            if (message) setMessage('');
                        }} 
                        placeholder="Escribe tu nuevo nombre de usuario..." 
                        className={`flex-1 px-4 py-3 bg-slate-50 border ${message ? 'border-red-500 focus:ring-red-500' : 'border-slate-300 focus:ring-blue-500'} rounded-xl focus:ring-2 outline-none transition-all`}
                    />
                    <button 
                        onClick={handleUpdateName}
                        className="bg-slate-800 hover:bg-slate-900 text-white font-semibold py-3 px-6 rounded-xl transition-colors shadow-md"
                    >
                        Actualizar Nombre
                    </button>
                </div>
                {/* MENSAJE DE ERROR VISUAL */}
                {message && (
                    <p className="text-red-500 text-sm mt-2 font-medium">⚠️ {message}</p>
                )}
            </div>

        </MainLayout>
    );
}