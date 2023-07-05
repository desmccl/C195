package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.helper.AppointmentQuery;
import com.example.test2.helper.CustomerQuery;
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
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

public class Appointment implements Initializable {
    public TextField description;
    public TextField location;
    public ComboBox<Appointments> type;
    public DatePicker date;
    public ComboBox<Appointments> time;
    public RadioButton month;
    public RadioButton week;
    public RadioButton all;
    @FXML
    private ComboBox<Appointments> cusID;
    @FXML
    private ComboBox<Appointments> userID;
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
    private TableColumn <Appointments, Timestamp> startcol;
    @FXML
    private TableColumn <Appointments, Timestamp> endcol;
    @FXML
    private ComboBox<Appointments> contacts;

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddApp (ActionEvent event) throws IOException {
        try {
            String appTitle = title.getText();
            int appContacts = contacts.getValue().getContactId();
            int appCustomer = cusID.getValue().getCustomerId();
            int appUser = userID.getValue().getUserId();
            String appDescription = description.getText();
            String appLocation = location.getText();
            String appType = type.getValue().getType();
            String appDate = date.getDayCellFactory().toString();
            //String appTime = time.getValue().getStart();

            //AppointmentQuery.Insert(appTitle, appDescription, appLocation, appType, appDate, appCustomer, appUser, appContacts, appTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateApp (ActionEvent event) throws IOException {
        try{
            String appTitle = title.getText();
            int appContacts = contacts.getValue().getContactId();
            int appCustomer = cusID.getValue().getCustomerId();
            int appUser = userID.getValue().getUserId();
            String appDescription = description.getText();
            String appLocation = location.getText();
            String appType = type.getValue().getType();
            String appDate = date.getDayCellFactory().toString();
            //String appTime = time.getValue().getStart();

            //AppointmentQuery.Update();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteApp (ActionEvent event) throws IOException, SQLException {
        Appointments selectedAppointment = appointments.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Selected appointment will be deleted, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            int appointmentId = (selectedAppointment.getAppointmentId());
            AppointmentQuery.delete(appointmentId);

        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointment.fxml"));
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
        startcol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endcol.setCellValueFactory(new PropertyValueFactory<>("end"));
    }


}
