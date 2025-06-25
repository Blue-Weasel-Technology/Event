package com.criss.event.location;

public class Location {
    private String city;
    private String address;
    private double latitude, longitude;
    private String venueName;
    private String type; // Ex. restaurant, bar, etc

    public Location() {
        // Default constructor
    }
    public Location(String city, String address, double latitude, double longitude, String venueName, String type) {
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.venueName = venueName;
        this.type = type;
    }

    // #region Getters and Setters, used to Collapse Block
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
    this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    // #endregion
}
