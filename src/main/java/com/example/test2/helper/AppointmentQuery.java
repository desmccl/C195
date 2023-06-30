package com.example.test2.helper;

import com.example.test2.dao.JDBC;
import com.example.test2.model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String start = rs.getString("Start");
            String end = rs.getString("End");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointments appointments = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactID);
            appointmentsList.add(appointments);
        }
        return appointmentsList;

    }
}
