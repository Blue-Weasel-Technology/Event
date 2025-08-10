package com.criss.event.scraping;
import java.sql.SQLException;
import java.sql.Statement;

public class EventAdd {


    static boolean firstIteration = true;
    public static int add(String description, java.sql.Timestamp eventDateTime, String image, double latitude, String locationDescription, double longitude, String name, String style) {
        var sql = "INSERT INTO events(description, event_date_time, image, latitude, location_description, longitude, name, style) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (var conn =  DB.connect();
             var pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             if (firstIteration) {
                try (var stmt = conn.createStatement()) {
                    stmt.executeUpdate("TRUNCATE TABLE events RESTART IDENTITY");
            }
                firstIteration = false;
        }

            // bind the values
            pstmt.setString(1, description);
            pstmt.setTimestamp(2, eventDateTime);
            pstmt.setString(3, image);
            pstmt.setDouble(4, latitude);
            pstmt.setString(5, locationDescription);
            pstmt.setDouble(6, longitude);
            pstmt.setString(7, name );
            pstmt.setString(8, style);



            // execute the INSERT statement and get the inserted id
            int insertedRow = pstmt.executeUpdate();
            if (insertedRow > 0) {
                var rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}