package com.example.test2.helper;

import com.example.test2.dao.JDBC;
import com.example.test2.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class LoginQuery {

    public static boolean selectCredentials (String userName, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

    public static ObservableList<Users> select2() throws SQLException {
        String sql = "SELECT * FROM Users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Users> usersList = FXCollections.observableArrayList();

        while (rs.next()) {
            String userId = rs.getString("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");

            Users users = new Users(userId, userName, password);
            usersList.add(users);
        }
        return usersList;
    }
}
