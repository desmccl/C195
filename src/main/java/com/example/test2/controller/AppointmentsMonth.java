package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.helper.AppointmentQuery;
import com.example.test2.model.Appointments;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AppointmentsMonth implements Initializable {

    public Button backmonth;
    public ComboBox <String> monthpick;
    public ComboBox <Appointments> typepick;
    public Label numofapp;
    public ObservableList<Appointments> typeList = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;

    @FXML
    void onActionBackMonth (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private void populatetypecombobox() {
        try {
            typeList = AppointmentQuery.select();
            typepick.setItems(typeList);
        } catch (SQLException e) {

        }
    }

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



