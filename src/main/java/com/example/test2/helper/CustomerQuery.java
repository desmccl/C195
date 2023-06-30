package com.example.test2.helper;

import com.example.test2.dao.JDBC;
import com.example.test2.model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public abstract class CustomerQuery {

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

    public static int delete(int customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }


}
