package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.dao.AppointmentQuery;
import com.example.test2.dao.CustomerQuery;
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
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;

/**This is the controller for the customers page that appears after login*/
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

    /**this method checks for appointments within 15 mins of a user logging in by getting the current time on the users device and checking
     * it against scheduled appointments. It also lets a user know when an appointment started already*/
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
            int appointmentID = existingAppointment.getAppointmentId();

            long minutesDifference = ChronoUnit.MINUTES.between(appointmentAlert,start);

            if (minutesDifference >= 0 && minutesDifference <= 15) {


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointment");
                alert.setContentText("You have an appointment in approx " + minutesDifference + " min(s)\n" + "Appointment ID: " + appointmentID + "\n" + start);
                alert.showAndWait();

                hasAlert = true;
            } else if (minutesDifference >= -15 && minutesDifference <= -15) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointment");
                alert.setContentText("Appointment started approx " + minutesDifference + " min(s) ago! \n" + "Appointment ID: " + appointmentID + "\n" + start);
                alert.showAndWait();

                hasAlert = true;
            }
        }

        return hasAlert;
    }

    /**this is the event handler to go to the add customer form*/
    @FXML
    void onActionAdd (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**this is the event handler to go to the custom report that counts how many customers there are in the system*/
    @FXML
    void onActionCusSystem (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customreport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**this is the event handler to go to the add customer form, it uses the sendCustomer() method from the add customer controller to pass customer
     * information over to be updated*/
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

    /**this is the event handler to delete a customer, it does not allow a user to delete a customer with appointments scheduled
     * and provides appropriate messaging for errors and confirmation*/
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

    /**this is the event handler to go to the appointment scheduler page*/
    @FXML
    void onActionSchedule (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**this is the event handler to go to the report for appointments by month, type, and count*/
    @FXML
    void onActionAppMonth (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointmentsmonth.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**this is the event handler to go to the report that shows appointments for each contact*/
    @FXML
    void onActionContactsApp (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("contactsappointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**this is the event handler to go back to the login page*/
    @FXML
    void onActionBack (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**this is the initialize method, it sets the customer information to the tableview using the select() method in the corresponding query class
     * and calls the appointmentAlert() method to display any alerts about upcoming or past appointments within 15 mins*/
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

        LocalDateTime currentSystemTime = LocalDateTime.now();
        appointmentAlert(currentSystemTime);
    }
}
