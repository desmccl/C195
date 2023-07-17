package com.example.test2.dao;

import com.example.test2.helper.JDBC;
import com.example.test2.helper.TimeConverterUtil;
import com.example.test2.model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**This class handles all SQL for communicating to the database to get appointment information*/
public abstract class AppointmentQuery {
    /**This select method pulls all unique* information about appointments from the database and puts it into an observable list*/
    public static ObservableList<Appointments> select() throws SQLException{
        String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End, a.customer_ID, a.User_ID, c.Contact_Name FROM APPOINTMENTS a INNER JOIN Contacts c ON a.Contact_ID = c.Contact_ID";
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
            String customerId = rs.getString("Customer_ID");
            int userId = rs.getInt("User_ID");
            String contactID = rs.getString("Contact_Name");

            start = TimeConverterUtil.convertToUserTimeZone(start);
            end = TimeConverterUtil.convertToUserTimeZone(end);



            Appointments appointments = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactID);
            appointmentsList.add(appointments);
        }
        return appointmentsList;

    }



    /**This select method pulls all information about appointments for each contact from the database and puts it into an observable list*/
    public static ObservableList<Appointments> selectAppointmentsForContact(int contactId) throws SQLException {
        String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End, a.customer_ID, a.User_ID, c.Contact_Name " +
                "FROM APPOINTMENTS a " +
                "INNER JOIN Contacts c ON a.Contact_ID = c.Contact_ID " +
                "WHERE c.Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
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
            String customerId = rs.getString("Customer_ID");
            int userId = rs.getInt("User_ID");
            String contactID = rs.getString("Contact_Name");

            start = TimeConverterUtil.convertToUserTimeZone(start);
            end = TimeConverterUtil.convertToUserTimeZone(end);

            Appointments appointments = new Appointments(appointmentId, title, description, location, type, start, end, customerId, userId, contactID);
            appointmentsList.add(appointments);
        }

        return appointmentsList;
    }

    /**This update method updates all information about appointments from the application and stores it in the database*/
    public static int Update(String appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        start = TimeConverterUtil.convertToUtcTimeZone(start);
        end = TimeConverterUtil.convertToUtcTimeZone(end);

        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?,Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, Integer.parseInt(appointmentId));

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /**This insert method inserts all new information about an appointment from the application and stores it in the database*/
    public static int Insert(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        start = TimeConverterUtil.convertToUtcTimeZone(start);
        end = TimeConverterUtil.convertToUtcTimeZone(end);

        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**This delete method deletes all information about an appointment from the application and database*/
    public static int delete(int appointmentId) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
