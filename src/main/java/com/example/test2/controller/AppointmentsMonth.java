package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.dao.AppointmentQuery;
import com.example.test2.model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**this is the controller for the total number of customer appointments by type and month report*/
public class AppointmentsMonth implements Initializable {
    @FXML
    private Button backmonth;
    @FXML
    private ComboBox <String> monthpick;
    @FXML
    private ComboBox <Appointments> typepick;
    @FXML
    private Label numofapp;
    private ObservableList<Appointments> typeList = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;

    /**this is the event handler to go back to the previous page*/
    @FXML
    void onActionBackMonth (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**this populates the type combobox utilizing the select() method in the corresponding query class*/
    private void populatetypecombobox() {
        try {
            typeList = AppointmentQuery.select();
            typepick.setItems(typeList);
        } catch (SQLException e) {

        }
    }

    /**this displays the number of appointments by type and month selected from the comboboxes in a label on the form*/
    private void updateAppointmentCount() {
        String selectedMonth = monthpick.getValue();
        Appointments selectedType = typepick.getValue();

        if (selectedMonth != null && selectedType != null) {
            int count = 0;

            // Iterate through the appointment list and count the matching appointments
            for (Appointments appointment : typeList) {
                String appointmentMonth = appointment.getStart().format(DateTimeFormatter.ofPattern("MMMM"));
                String appointmentType = appointment.getType();

                if (appointmentMonth.equals(selectedMonth) && appointmentType.equals(selectedType.getType())) {
                    count++;
                }
            }

            numofapp.setText(String.valueOf(count));
        } else {
            numofapp.setText("");
        }
    }

    /**this is the initialize method, it gets the months and sets them to the corresponding combobox starting with the current month
     * it also calls the method to populate the type combobox,
     * and uses lambda expressions to set listeners on the comboboxes to set the number in the label appropriately*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");

        LocalDateTime currentDateTime = LocalDateTime.now();
        String currentMonth = currentDateTime.format(formatter);
        monthpick.getItems().add(currentMonth);

        for (int i = 1; i <= 11; i++) {
            LocalDateTime nextMonthDateTime = currentDateTime.plusMonths(i);
            String nextMonth = nextMonthDateTime.format(formatter);
            monthpick.getItems().add(nextMonth);
        }

        populatetypecombobox();

        //lambda to use method for event listeners to update the label based on the combobox choices
        monthpick.setOnAction(event -> updateAppointmentCount());
        typepick.setOnAction(event -> updateAppointmentCount());
    }
}



