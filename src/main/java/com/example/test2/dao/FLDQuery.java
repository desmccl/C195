package com.example.test2.dao;

import com.example.test2.helper.JDBC;
import com.example.test2.model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This class handles all SQL for communicating to the database to get division information*/
public abstract class FLDQuery {

    /**This select2 method gets all the information about divisions from the database and puts it into an observable list*/
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
