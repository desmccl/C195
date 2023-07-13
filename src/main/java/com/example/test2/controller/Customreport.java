package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.dao.CustomerQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**this is the controller for the third custom report, it counts customers in the system*/
public class Customreport implements Initializable {
    @FXML
    private Label cusinsystem;
    Stage stage;
    Parent scene;

    /**this is the event handler to go back to the previous page*/
    @FXML
    void onActionBack5 (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**this is the initialize method, it gets the number of customers in the system using the select2() method from the corresponding query class
     * and sets it to the label*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int customersNumber;
        try {
            customersNumber = CustomerQuery.select2();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cusinsystem.setText(String.valueOf(customersNumber));
    }
}
