package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.helper.AppointmentQuery;
import com.example.test2.model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Appointment implements Initializable {
    private ComboBox cusID;
    @FXML
    private ComboBox userID;
    @FXML
    private TextField appID;
    @FXML
    private TextField title;
    @FXML
    private Button deleteapp;
    @FXML
    private Button updateapp;
    @FXML
    private Button addapp;
    @FXML
    private TableView <Appointments> appointments;
    @FXML
    private TableColumn <Appointments, Integer> contactidcol;
    @FXML
    private TableColumn <Appointments, Integer> customeridcol;
    @FXML
    private TableColumn <Appointments, Integer> appidcol;
    @FXML
    private TableColumn <Appointments, Integer> useridcol;
    @FXML
    private TableColumn <Appointments, String> titlecol;
    @FXML
    private TableColumn <Appointments, String> descriptioncol;
    @FXML
    private TableColumn <Appointments, String> locationcol;
    @FXML
    private TableColumn <Appointments, String> typecol;
    @FXML
    private TableColumn datecol;
    @FXML
    private TableColumn timecol;
    @FXML
    private ComboBox contacts;

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddApp (ActionEvent event) throws IOException {


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateApp (ActionEvent event) throws IOException {


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteApp (ActionEvent event) throws IOException {


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Appointments> appointmentsList = null;
        try {
            appointmentsList = AppointmentQuery.select();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        appointments.setItems(appointmentsList);

        contactidcol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        customeridcol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appidcol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        useridcol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationcol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
    }


}
