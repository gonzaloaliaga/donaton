import { useContext, useState } from 'react';
import { AuthContext } from '../context/AuthContext';
import authService from "../services/authService";

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
    const { user, setUser, logout } = useContext(AuthContext);
    const [newName, setNewName] = useState('');

    const handleUpdateName = async () => {
        try {
            await authService.updateUsername(user.username, newName);

            setUser({ ...user, username: newName });
            alert("¡Nombre actualizado!");
            setNewName('');
        } catch (error) {
            console.error("Error:", error);
        }
    };

    if (!user) return null;

    return (
        <div className="profile-container">

            {/* ... Header y Avatar ... */}
            <header style={{ display: 'flex', gap: '15px', alignItems: 'center' }}>
                <div className="avatar">{user.username[0].toUpperCase()}</div>
                <h1>Bienvenido, {user.username}</h1>
                <button onClick={logout}>Salir</button>
            </header>

            <section style={{ margin: '20px 0' }}>
                {/* Dashboard según mapeo*/}
                {ROLE_STRATEGIES[user.role] || <p>Rol no reconocido</p>}
            </section>

            <footer style={{ borderTop: '1px solid #eee', paddingTop: '10px' }}>
                <input 
                    value={newName} 
                    onChange={(e) => setNewName(e.target.value)} 
                    placeholder="Cambiar nombre..." 
                />
                <button onClick={handleUpdateName}>Actualizar</button>
            </footer>
        </div>
    );
}