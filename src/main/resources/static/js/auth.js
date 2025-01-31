import { apiRequest } from "./api.js";

export const signup = async (userData) => {
    return apiRequest("/users/signup", "POST", userData);
};

export const login = async (email, password) => {
    try {
        const response = await api.post('/users/login', { email, password });
        
        if (response.data.token) {
            localStorage.setItem('jwtToken', response.data.token); // Store token
            window.location.href = '/bookFlight'; // Redirect after login
        }
        
        return response.data;
    } catch (error) {
        console.error('Login failed:', error);
        return null;
    }
};

export const logout = () => {
    localStorage.removeItem("token");
};

export const getToken = () => {
    return localStorage.getItem("token");
};
