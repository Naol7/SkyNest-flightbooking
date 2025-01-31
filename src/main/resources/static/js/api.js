import axios from 'axios';

const API_URL = 'http://localhost:3030/api';

const api = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Automatically add token to requests if it exists
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('jwtToken'); // Assuming you store the token in localStorage
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

export default api;
