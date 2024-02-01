package com.restaurationaws.reservationfinalizationservice.models;

public class Reservation {

    private String reservationId;
    private String date;
    private String email;
    private String firstName;
    private String lastName;
    private String tokenId;
    private String nummberOfGuests;


    public Reservation() {
    }
    public Reservation(String reservationId, String date, String email, String firstName, String lastName, String tokenId, String nummberOfGuests) {
        this.reservationId = reservationId;
        this.date = date;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tokenId = tokenId;
        this.nummberOfGuests = nummberOfGuests;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getNummberOfGuests() {
        return nummberOfGuests;
    }

    public void setNummberOfGuests(String nummberOfGuests) {
        this.nummberOfGuests = nummberOfGuests;
    }
}
