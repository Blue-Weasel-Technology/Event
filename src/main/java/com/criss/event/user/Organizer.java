package com.criss.event.user;

public class Organizer extends User {
    private String contactEmail;

    public Organizer() {
    super(); // calls User(), default constructor
        this.contactEmail = "";
    }       
    public Organizer(String name, int age, String contactEmail) {
        super(name, age);
        this.contactEmail = contactEmail;
    }

    // #region Getters and Setters, used to Collapse Block
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    // #endregion
}