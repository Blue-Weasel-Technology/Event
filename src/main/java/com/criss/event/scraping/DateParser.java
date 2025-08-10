package com.criss.event.scraping;

import java.sql.Timestamp;
import java.time.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {

    private static final Map<String, Month> MONTHS = new HashMap<>();

    static {
        MONTHS.put("ianuarie", Month.JANUARY);
        MONTHS.put("februarie", Month.FEBRUARY);
        MONTHS.put("martie", Month.MARCH);
        MONTHS.put("aprilie", Month.APRIL);
        MONTHS.put("mai", Month.MAY);
        MONTHS.put("iunie", Month.JUNE);
        MONTHS.put("iulie", Month.JULY);
        MONTHS.put("august", Month.AUGUST);
        MONTHS.put("septembrie", Month.SEPTEMBER);
        MONTHS.put("octombrie", Month.OCTOBER);
        MONTHS.put("noiembrie", Month.NOVEMBER);
        MONTHS.put("decembrie", Month.DECEMBER);
    }

    public static Timestamp parse(String dateStr) {

        // Regex to capture: day, month, optional year, time
        Pattern pattern = Pattern.compile("(\\d{1,2})\\s+(\\p{L}+)(?:\\s+'(\\d{2}))?.*?ora\\s+(\\d{1,2}):(\\d{2})");
        Matcher matcher = pattern.matcher(dateStr);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Date string format not recognized: " + dateStr);
        }

        int day = Integer.parseInt(matcher.group(1));
        String monthName = matcher.group(2);
        Month month = MONTHS.get(monthName);
        if (month == null) {
            throw new IllegalArgumentException("Unknown month: " + monthName);
        }

        Integer year = null;
        if (matcher.group(3) != null) {
            int shortYear = Integer.parseInt(matcher.group(3));
            year = 2000 + shortYear;
        }

        int hour = Integer.parseInt(matcher.group(4));
        int minute = Integer.parseInt(matcher.group(5));

        LocalDateTime now = LocalDateTime.now();
        if (year == null) {
            // Assume current year
            LocalDateTime possibleDate = LocalDateTime.of(now.getYear(), month, day, hour, minute);
            if (possibleDate.isBefore(now)) {
                possibleDate = possibleDate.plusYears(1);
            }
            return Timestamp.valueOf(possibleDate);
        } else {
            return Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, minute));
        }
    }
}