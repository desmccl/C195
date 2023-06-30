package com.example.test2.helper;

import com.example.test2.dao.JDBC;
import com.example.test2.model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FLDQuery {
    public static ObservableList<FirstLevelDivisions> select2() throws SQLException {
        String sql = "SELECT Division_ID, Division, Country_ID FROM First_Level_Divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<FirstLevelDivisions> divisionNames = FXCollections.observableArrayList();

        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");

            FirstLevelDivisions firstLevelDivisions = new FirstLevelDivisions(divisionId, division, countryId);
            divisionNames.add(firstLevelDivisions);
        }
        return divisionNames;
    }
}
