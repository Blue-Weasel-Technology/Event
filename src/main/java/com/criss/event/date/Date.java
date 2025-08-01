package com.criss.event.date;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data               // Lombok annotation to auto-generate getters, setters, toString(), equals(), hashCode(), and constructors
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
public class Date {
    private int day, month, year;
    private int hour, minute;  //Momentan lucaram cu fusul orar UTC+3, adica al Romaniei

    public String getDate(){
        return day+ "/" + month + "/" + year + " " + hour + ":" + minute;
    }
}
