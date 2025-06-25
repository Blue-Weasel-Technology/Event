package com.criss.event.date;

public class Date {
    private int day, month, year;
    private int hour, minute;  //Momentan lucaram cu fusul orar UTC+3, adica al Romaniei

    public String getDate(){
        return day+ "/" + month + "/" + year + " " + hour + ":" + minute;
    }
    public Date() {
        // Default constructor
    }
    Date(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    // #region Getters and Setters, used to Collapse Block
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year){
        this.year = year;
    }
    public int getHour() {
    return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    // #endregion
}
