import { apiRequest } from "./api.js";
import { getToken } from "./auth.js";

export const bookFlight = async (bookingData) => {
    return apiRequest("/bookings", "POST", bookingData, getToken());
};

export const getUserBookings = async () => {
    return apiRequest("/bookings", "GET", null, getToken());
};

export const cancelBooking = async (bookingId) => {
    return apiRequest(`/bookings/${bookingId}`, "DELETE", null, getToken());
};
