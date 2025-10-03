package com.criss.event.scraping;

import java.sql.Timestamp;
import java.time.*;
import java.util.*;

public class DateParser {

    private static final Map<String, Integer> MONTHS = new HashMap<>();

    static {
        MONTHS.put("ianuarie", 1);
        MONTHS.put("februarie", 2);
        MONTHS.put("martie", 3);
        MONTHS.put("aprilie", 4);
        MONTHS.put("mai", 5);
        MONTHS.put("iunie", 6);
        MONTHS.put("iulie", 7);
        MONTHS.put("august", 8);
        MONTHS.put("septembrie", 9);
        MONTHS.put("octombrie", 10);
        MONTHS.put("noiembrie", 11);
        MONTHS.put("decembrie", 12);
    }

    public static Timestamp parse(String dateStr) {
            dateStr = dateStr.toLowerCase();      // lowercase all letters
            int year=-1;       //OK
            int month=-1;  //OK
            int day=-1;    
            int hour=-1;   //OK
            int minute=-1;     //OK
            LocalDate currentDate = LocalDate.now();        // get the current date
            year = currentDate.getYear();       // get the current year

            // search for month

            for (Map.Entry<String, Integer> entry : MONTHS.entrySet()) 
                if (dateStr.contains(entry.getKey())) {
                    int monthIndex=dateStr.indexOf(entry.getKey());
                    day=Integer.parseInt(dateStr.substring(monthIndex-3, monthIndex).strip());
                    month = entry.getValue();
                    break;
                }
            
            // search for hour and minute
            int collonIndex = dateStr.indexOf(":");
            String hourString = dateStr.substring(collonIndex-2, collonIndex).strip();
            String minuteString = dateStr.substring(collonIndex+1, collonIndex+3).strip();
            hour = Integer.parseInt(hourString);
            minute = Integer.parseInt(minuteString);

            // search for real year
            if(dateStr.contains("\'")) {
                int apostropheIndex = dateStr.indexOf("\'");
                try {
                    year = Integer.parseInt(dateStr.substring(apostropheIndex+1, apostropheIndex+3).strip())+2000;
                } catch(Exception e) {System.err.println("Year not found!");}
                
            }
            if(year == -1 || month == -1 || day == -1 || hour == -1 || minute ==-1)
                System.err.println("DATE COULD NOT BE PARSED!");
            return Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, minute));
    }
}