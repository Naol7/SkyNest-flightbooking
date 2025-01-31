import { apiRequest } from "./api.js";
import { getToken } from "./auth.js";

export const searchFlights = async (query) => {
    return apiRequest(`/flights/search?${new URLSearchParams(query)}`);
};

export const getFlightDetails = async (flightId) => {
    return apiRequest(`/flights/${flightId}`);
};

export const createFlight = async (flightData) => {
    return apiRequest("/flights", "POST", flightData, getToken());
};

export const updateFlight = async (flightId, flightData) => {
    return apiRequest(`/flights/${flightId}`, "PUT", flightData, getToken());
};

export const deleteFlight = async (flightId) => {
    return apiRequest(`/flights/${flightId}`, "DELETE", null, getToken());
};
