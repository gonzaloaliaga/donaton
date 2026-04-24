import { useContext, useState } from 'react';
import { AuthContext } from '../context/AuthContext';

export default function Profile() {
    const { user, setUser, logout } = useContext(AuthContext);
    const [newName, setNewName] = useState('');

    return (
        <div>
            <nav style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                <div style={{ background: 'blue', color: 'white', borderRadius: '50%', width: '40px', textAlign: 'center' }}>
                    {user?.charAt(0).toUpperCase()} {/* Avatar: primera letra del username */}
                </div>
                <button onClick={logout}>Cerrar Sesión</button>
            </nav>
            <h1>Bienvenido, {user}</h1>
            <input placeholder="Nuevo username" onChange={(e) => setNewName(e.target.value)} />
            <button onClick={() => setUser(newName)}>Guardar Cambios</button>
        </div>
    );
}