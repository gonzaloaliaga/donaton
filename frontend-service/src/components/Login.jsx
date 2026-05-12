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
            const mensajeError = error.message === "Failed to fetch" 
                ? "Error de conexión con el servidor."
                : "Credenciales incorrectas.";
            setError(mensajeError);
        }
    };

    return (
        // Fondo azul que ocupa toda la pantalla
        <div className="min-h-screen flex items-center justify-center bg-blue-600 px-4">
            
            {/* El "Marco" (Tarjeta blanca) que envuelve toda la información */}
            <div className="bg-white p-10 rounded-[2.5rem] shadow-2xl w-full max-w-sm flex flex-col items-center">
                
                {/* Logo y Título dentro del marco */}
                <div className="flex flex-col items-center mb-8">
                    <img 
                        src="/favicon.svg" 
                        alt="Logo Donatón" 
                        className="w-16 h-16 mb-3" 
                    />
                    <h1 className="text-2xl font-bold text-slate-800 tracking-tight">
                        Donatón
                    </h1>
                </div>
                
                {/* Formulario alineado dentro del marco */}
                <form onSubmit={handleLogin} className="w-full space-y-4 flex flex-col items-center">
                    
                    {/* Input de Usuario */}
                    <input 
                        type="text" 
                        placeholder="ingrese su usuario" 
                        value={username}
                        onChange={(e) => setUsername(e.target.value)} 
                        className="w-full px-4 py-3 bg-slate-50 border border-slate-200 rounded-xl focus:border-blue-400 outline-none transition-all placeholder:text-slate-400 text-sm"
                        required 
                    />
                    
                    {/* Input de Contraseña */}
                    <input 
                        type="password" 
                        placeholder="ingrese su contraseña" 
                        value={password}
                        onChange={(e) => setPassword(e.target.value)} 
                        className="w-full px-4 py-3 bg-slate-50 border border-slate-200 rounded-xl focus:border-blue-400 outline-none transition-all placeholder:text-slate-400 text-sm"
                        required 
                    />

                    {/* Mensaje de Error */}
                    {error && (
                        <p className="text-xs text-red-500 font-medium py-1">
                            {error}
                        </p>
                    )}

                    {/* Botón Ingresar: alineado y con efecto azul al clickear */}
                    <button 
                        type="submit" 
                        className="w-full bg-slate-800 text-white font-bold py-3.5 rounded-xl transition-all duration-200 hover:bg-slate-700 active:bg-blue-600 shadow-md mt-2"
                    >
                        ingresar
                    </button>
                    
                </form>
            </div>
        </div>
    );
}