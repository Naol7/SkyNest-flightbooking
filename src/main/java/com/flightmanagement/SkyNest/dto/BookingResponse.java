package com.flightmanagement.SkyNest.dto;

public class BookingResponse {
    private Long bookingId;
    private Long userId;
    private String username;
    private Long flightId;
    private int seatCount;

    public BookingResponse(Long bookingId, Long userId, String username, Long flightId, int seatCount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.username = username;
        this.flightId = flightId;
        this.seatCount = seatCount;
    }

    // Getters and setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
