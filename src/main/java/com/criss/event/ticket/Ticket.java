package com.criss.event.ticket;

public class Ticket {
    public double price;
    public String currency;
    public String type; //Ex. VIP, Standard, 3-day pass etc.
    public int quantity;

    public Ticket() {
        // Default constructor
    }
    public Ticket(double price, String currency, String type, int quantity) {
        this.price = price;
        this.currency = currency;
        this.type = type;
        this.quantity = quantity;
    }

    // #region Getters and Setters, used to Collapse Block
    public double getPrice() {
    return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    // #endregion
}