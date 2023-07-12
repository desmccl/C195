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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

public class Customers implements Initializable {
    @FXML
    private Button numcusinsystem;
    @FXML
    private Button backlogin;
    @FXML
    private Button addcus;
    @FXML
    private Button updatecus;
    @FXML
    private Button deletecus;
    @FXML
    private Button schedule;
    @FXML
    private Button appmonth;
    @FXML
    private Button appcontacts;
    @FXML
    private TableColumn <Customers, String> cusnamecol;
    @FXML
    private TableColumn <Customers, String> addresscol;
    @FXML
    private TableColumn <Customers, String> postcodecol;
    @FXML
    private TableColumn <Customers, String> phonecol;
    @FXML
    private TableColumn <Customers, String> divisionidcol;
    @FXML
    private TableColumn <Customers, Integer> cusidcol;
    @FXML
    private TableView<com.example.test2.model.Customers> custable;



    Stage stage;
    Parent scene;

    private boolean appointmentAlert(LocalDateTime appointmentAlert) {

        ObservableList<Appointments> appointmentsList = null;
        try {
            appointmentsList = AppointmentQuery.select();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean hasAlert = false;

        for (Appointments existingAppointment : appointmentsList) {
            LocalDateTime start = existingAppointment.getStart();

            // Calculate the time difference between the current system time and the appointment start time
            long minutesDifference = ChronoUnit.MINUTES.between(appointmentAlert,start);

            // Check if the appointment is within 15 minutes from the current system time
            if (minutesDifference >= 0 && minutesDifference <= 15) {
                // Print a notification message to the console
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointment");
                alert.setContentText("You have an appointment in approx " + minutesDifference + " min(s)");
                alert.showAndWait();

                // Set the flag to indicate that an alert exists
                hasAlert = true;
            } else if (minutesDifference >= -15) {
                // Print a notification message to the console
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointment");
                alert.setContentText("Appointment started approx " + minutesDifference + " min(s) ago!");
                alert.showAndWait();

                hasAlert = true;
            }
        }

        return hasAlert;
    }




    @FXML
    void onActionAdd (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionCusSystem (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customreport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    void onActionUpdate (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("addCustomer.fxml"));
        loader.load();

        AddCustomer updateCustomer = loader.getController();
        updateCustomer.sendCustomer(custable.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDelete (ActionEvent event) throws IOException, SQLException {
        com.example.test2.model.Customers selectedCustomer = custable.getSelectionModel().getSelectedItem();

        if (!selectedCustomer.getAppointmentsList().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please delete all appointments first");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Selected customer will be deleted, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            int customerId = Integer.parseInt(selectedCustomer.getCustomerId());
            CustomerQuery.delete(customerId);

        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Customer not deleted");
            errorAlert.showAndWait();
        }

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSchedule (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAppMonth (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointmentsmonth.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionContactsApp (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("contactsappointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionBack (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<com.example.test2.model.Customers> customerList = CustomerQuery.select();
            custable.setItems(customerList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cusidcol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        cusnamecol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postcodecol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionidcol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        //appointmentAlert(LocalDateTime.from(LocalTime.now()));

        LocalDateTime currentSystemTime = LocalDateTime.now();
        appointmentAlert(currentSystemTime);
    }
}
