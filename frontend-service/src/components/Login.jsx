import { useState, useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import authService from '../services/authService';

export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const { login } = useContext(AuthContext);

    const handleLogin = async (e) => {
        e.preventDefault();
        setError(null);

        try {
            const data = await authService.login(username, password);
            login(data);
        } catch (error) {
            setError(error.message)
            console.error("Error conectando al backend:", error);
        }
    };

    return (
        <div className="login-container">
            <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', gap: '10px', maxWidth: '300px' }}>
                <h2>Iniciar Sesión</h2>
                
                <input 
                    type="text" 
                    placeholder="Username" 
                    onChange={(e) => setUsername(e.target.value)} 
                    required 
                />
                
                <input 
                    type="password" 
                    placeholder="Password" 
                    onChange={(e) => setPassword(e.target.value)} 
                    required 
                />

                {/* Renderizado condicional del error */}
                {error && (
                    <div style={{ color: 'white', background: '#ff4444', padding: '10px', borderRadius: '4px', fontSize: '14px' }}>
                        ⚠️ {error}
                    </div>
                )}

                <button type="submit" style={{ padding: '10px', cursor: 'pointer' }}>
                    Ingresar
                </button>
            </form>
        </div>
    );
}