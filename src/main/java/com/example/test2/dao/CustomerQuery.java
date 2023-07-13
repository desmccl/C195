package com.example.test2.dao;

import com.example.test2.helper.JDBC;
import com.example.test2.model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**This class handles all SQL for communicating to the database to get customer information*/
public abstract class CustomerQuery {
    /**This select method pulls all information about customers from the database and puts it into an observable list*/
    public static ObservableList<Customers> select() throws SQLException {
        String sql = "SELECT c.Customer_ID, c.Customer_Name, c.Address, c.Postal_Code, c.Phone, d.Division FROM customers c INNER JOIN first_level_divisions d ON c.Division_ID = d.Division_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Customers> customerList = FXCollections.observableArrayList();

        while (rs.next()) {
            String customerId = String.valueOf(rs.getInt("Customer_ID"));
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String divisionId = rs.getString("Division");

            Customers customers = new Customers(customerId, customerName, address, postalCode, phone, divisionId);
            customerList.add(customers);
        }

        return customerList;
    }

    /**This select2 method pulls the number of customers from the database for use with the custom report*/
    public static int select2() throws SQLException {
        String sql = "SELECT COUNT(*) AS customerCount FROM Customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int customerCount = 0;

        if (rs.next()) {
            customerCount = rs.getInt("customerCount");
        }

        return customerCount;
    }

    /**This update method updates all information about customers from the application and puts it into the database*/
    public static int update(int customerId, String customerName, String address, String postalCode, String phone, int divisionID) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionID);
        ps.setInt(6, customerId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**This insertcus method inserts all information about customers from the application and puts it into the database*/
    public static int Insertcus(String customerName, String address, String postalCode, String phone, int divisionID) throws SQLException {
        String sql = "INSERT INTO Customers (Customer_Name, Address, Postal_Code, Phone, Division_Id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionID);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**This delete method deletes a customer and all corresponding information from the database and application*/
    public static int delete(int customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }


}
