import { useContext, useState } from 'react';
import { AuthContext } from '../context/AuthContext';

export default function Profile() {
    const { user, setUser, logout } = useContext(AuthContext);
    const [newName, setNewName] = useState('');

    const handleUpdateName = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/auth/update-username', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    currentUsername: user,
                    newUsername: newName
                })
            });

            if (response.ok) {
                setUser(newName); // Actualiza el estado global de React
                alert("¡Nombre actualizado en el servidor!");
            } else {
                alert("Error al actualizar");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    };

    return (
        <div>
            {/* ... tu nav ... */}
            <h1>Bienvenido, {user}</h1>
            <div style={{ marginTop: '20px' }}>
                <input
                    placeholder="Nuevo username"
                    value={newName}
                    onChange={(e) => setNewName(e.target.value)}
                />
                <button onClick={handleUpdateName}>Guardar Cambios en Backend</button>

                <button onClick={logout}>Cerrar sesión</button>
            </div>
        </div>
    );
}