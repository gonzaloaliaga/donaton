import { useContext } from 'react';
import { AuthContext } from '../../context/AuthContext';

const AdminDashboard = () => {
    const { logout, user } = useContext(AuthContext); 

    return (
        <div className="min-h-screen bg-slate-50">
            {/* Barra de Navegación Superior */}
            <nav className="bg-white border-b border-slate-200 px-6 py-4 flex justify-between items-center shadow-sm sticky top-0 z-10">
                <div className="flex items-center gap-3">
                    <div className="w-10 h-10 bg-blue-600 rounded-xl flex items-center justify-center shadow-md shadow-blue-500/20">
                       <span className="text-white font-bold text-xl">D</span>
                    </div>
                    <h1 className="text-xl font-bold text-slate-800 hidden sm:block">Donaton Admin</h1>
                </div>
                <div className="flex items-center gap-4">
                    <span className="text-sm font-medium text-slate-600 bg-slate-100 px-4 py-2 rounded-full hidden sm:block">
                        Hola, {user?.username || 'Administrador'}
                    </span>
                    <button 
                        onClick={logout}
                        className="text-sm font-bold text-red-600 hover:text-red-700 hover:bg-red-50 px-4 py-2 rounded-lg transition-colors border border-transparent hover:border-red-100"
                    >
                        Cerrar Sesión
                    </button>
                </div>
            </nav>

            {/* Contenido Principal */}
            <main className="max-w-7xl mx-auto px-4 sm:px-6 py-8">
                <div className="mb-8">
                    <h2 className="text-2xl font-bold text-slate-800">Panel General</h2>
                    <p className="text-slate-500 mt-1">Resumen de la actividad de los microservicios.</p>
                </div>
                
                {/* Tarjetas de Estadísticas (Grid) */}
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
                    <div className="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 flex items-center gap-4 hover:shadow-md transition-shadow">
                        <div className="w-14 h-14 bg-blue-50 text-blue-600 rounded-2xl flex items-center justify-center text-2xl">👥</div>
                        <div>
                            <p className="text-sm font-semibold text-slate-500">Usuarios Totales</p>
                            <p className="text-3xl font-black text-slate-800 mt-1">124</p>
                        </div>
                    </div>
                    
                    <div className="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 flex items-center gap-4 hover:shadow-md transition-shadow">
                        <div className="w-14 h-14 bg-emerald-50 text-emerald-600 rounded-2xl flex items-center justify-center text-2xl">📦</div>
                        <div>
                            <p className="text-sm font-semibold text-slate-500">Donaciones Activas</p>
                            <p className="text-3xl font-black text-slate-800 mt-1">38</p>
                        </div>
                    </div>
                    
                    <div className="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 flex items-center gap-4 hover:shadow-md transition-shadow">
                        <div className="w-14 h-14 bg-purple-50 text-purple-600 rounded-2xl flex items-center justify-center text-2xl">🚚</div>
                        <div>
                            <p className="text-sm font-semibold text-slate-500">Logística en curso</p>
                            <p className="text-3xl font-black text-slate-800 mt-1">12</p>
                        </div>
                    </div>
                </div>

                {/* Área de Tabla de Datos */}
                <div className="bg-white rounded-2xl shadow-sm border border-slate-100 overflow-hidden">
                    <div className="px-6 py-5 border-b border-slate-100 bg-slate-50/50 flex justify-between items-center">
                        <h3 className="text-lg font-bold text-slate-800">Últimos Movimientos</h3>
                        <button className="text-sm text-blue-600 font-semibold hover:text-blue-800">Ver todos</button>
                    </div>
                    <div className="p-12 text-center flex flex-col items-center justify-center">
                        <div className="w-16 h-16 bg-slate-100 rounded-full flex items-center justify-center text-3xl mb-4">⚙️</div>
                        <p className="text-slate-500 font-medium">Aquí conectaremos los datos reales del BFF-Service.</p>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default AdminDashboard;