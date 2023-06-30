package com.example.test2.helper;

import com.example.test2.dao.JDBC;

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
}
