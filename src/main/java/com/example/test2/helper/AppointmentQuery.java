package com.example.test2.helper;

import com.example.test2.dao.JDBC;
import com.example.test2.model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class AppointmentQuery {
    public static ObservableList<Appointments> select() throws SQLException{
        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        while (rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp starting = rs.getTimestamp("Start");
            LocalDateTime start = starting.toLocalDateTime();
            Timestamp ending = rs.getTimestamp("End");
            LocalDateTime end = ending.toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointments appointments = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactID);
            appointmentsList.add(appointments);
        }
        return appointmentsList;

    }

    public static int Update(int appointmentId, String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?,Customer_ID = ?, User_ID = ?, Contact_ID WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, type);
        ps.setString(6, start);
        ps.setString(7, end);
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int Insert(String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setString(5, start);
        ps.setString(6, end);
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int delete(int appointmentId) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
