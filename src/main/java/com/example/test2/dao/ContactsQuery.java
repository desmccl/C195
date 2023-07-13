package com.example.test2.dao;

import com.example.test2.helper.JDBC;
import com.example.test2.model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This class handles all SQL for communicating to the database to get contact information*/
public abstract class ContactsQuery {

    /**This select method pulls all information about contacts from the database and puts it into an observable list*/
    public static ObservableList<Contacts> select() throws SQLException {
        String sql = "SELECT * FROM Contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();

        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contacts contacts = new Contacts(contactId,contactName,email);
            contactsList.add(contacts);
        }
        return contactsList;
    }
}
