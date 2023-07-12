package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.helper.AppointmentQuery;
import com.example.test2.helper.ContactsQuery;
import com.example.test2.helper.CustomerQuery;
import com.example.test2.helper.LoginQuery;
import com.example.test2.model.Appointments;
import com.example.test2.model.Contacts;
import com.example.test2.model.Customers;
import com.example.test2.model.Users;
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
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Appointment implements Initializable {
    @FXML
    private TextField description;
    @FXML
    private TextField location;
    @FXML
    private ComboBox<Appointments> type;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<LocalTime> time;
    @FXML
    private TableView appointments2;
    @FXML
    private TableView appointments3;
    @FXML
    private TableColumn <Appointments, Integer> contactidcol1;
    @FXML
    private TableColumn <Appointments, Integer> customeridcol1;
    @FXML
    private TableColumn <Appointments, Integer> appidcol1;
    @FXML
    private TableColumn <Appointments, Integer> useridcol1;
    @FXML
    private TableColumn <Appointments, String> titlecol1;
    @FXML
    private TableColumn <Appointments, String> descriptioncol1;
    @FXML
    private TableColumn <Appointments, String> locationcol1;
    @FXML
    private TableColumn <Appointments, String> typecol1;
    @FXML
    private TableColumn <Appointments, Timestamp> startcol1;
    @FXML
    private TableColumn <Appointments, Timestamp> endcol1;
    @FXML
    private TableColumn <Appointments, Integer> contactidcol11;
    @FXML
    private TableColumn <Appointments, Integer> customeridcol11;
    @FXML
    private TableColumn <Appointments, Integer> appidcol11;
    @FXML
    private TableColumn <Appointments, Integer> useridcol11;
    @FXML
    private TableColumn <Appointments, String> titlecol11;
    @FXML
    private TableColumn <Appointments, String> descriptioncol11;
    @FXML
    private TableColumn <Appointments, String> locationcol11;
    @FXML
    private TableColumn <Appointments, String> typecol11;
    @FXML
    private TableColumn <Appointments, Timestamp> startcol11;
    @FXML
    private TableColumn <Appointments, Timestamp> endcol11;
    @FXML
    private ComboBox<Customers> cusID;
    @FXML
    private ComboBox<Users> userID;
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
    private ComboBox<Contacts> contacts;
    public ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
    public ObservableList<Customers> customerList = FXCollections.observableArrayList();
    public ObservableList<Users> userList = FXCollections.observableArrayList();
    public ObservableList<Appointments> typeList = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;

    private boolean checkAppointmentOverlap(LocalDateTime newAppointmentDateTime) {
        ObservableList<Appointments> appointmentsList = null;
        try {
            appointmentsList = AppointmentQuery.select();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Appointments existingAppointment : appointmentsList) {
            LocalDateTime start = existingAppointment.getStart();
            LocalDateTime end = start.plusHours(1);

            if (newAppointmentDateTime.isBefore(end) && start.isBefore(newAppointmentDateTime.plusHours(1))) {
                return true;
            }
        }

        return false;
    }

    @FXML
    void onActionAddApp (ActionEvent event) throws IOException {
        try {
            String appTitle = title.getText();
            String appDescription = description.getText();
            String appLocation = location.getText();
            String appType = String.valueOf(type.getValue().getType());
            LocalDate appDate = LocalDate.parse(date.getValue().toString());
            LocalTime appTime = time.getValue();
            LocalDateTime appDateTime = LocalDateTime.of(appDate, appTime);
            String appCustomer = (cusID.getValue().getCustomerId());
            int appUser = Integer.parseInt(userID.getValue().getUserId());
            int appContacts = contacts.getValue().getContactId();



            if (checkAppointmentOverlap(appDateTime)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Appointment time overlaps!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Scheduled");
                alert.setContentText("Appointment has been scheduled");
                alert.showAndWait();

                AppointmentQuery.Insert(appTitle, appDescription, appLocation, String.valueOf(appType), appDateTime, appDateTime.plusHours(1), Integer.parseInt(appCustomer), appUser, appContacts);
            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter correct values.");
            alert.showAndWait();
        }


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSubmit (ActionEvent event) throws IOException {
        try {
            String appid = appID.getText();
            String appTitle = title.getText();
            String appDescription = description.getText();
            String appLocation = location.getText();
            String appType = String.valueOf(type.getValue().getType());
            LocalDate appDate = LocalDate.parse(date.getValue().toString());
            LocalTime appTime = time.getValue();
            LocalDateTime appDateTime = LocalDateTime.of(appDate, appTime);
            String appCustomer = (cusID.getValue().getCustomerId());
            int appUser = Integer.parseInt(userID.getValue().getUserId());
            int appContacts = contacts.getValue().getContactId();

            if (checkAppointmentOverlap(appDateTime)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Appointment time overlaps!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Scheduled");
                alert.setContentText("Appointment has been updated");
                alert.showAndWait();

                AppointmentQuery.Update(appid, appTitle, appDescription, appLocation, String.valueOf(appType), appDateTime, appDateTime.plusHours(1), Integer.parseInt(appCustomer), appUser, appContacts);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter correct values.");
            alert.showAndWait();
        }

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionUpdateApp (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("appointment.fxml"));
        loader.load();

        Appointment updateAppointment = loader.getController();
        updateAppointment.sendAppointment(appointments.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void sendAppointment(Appointments appointments) {
        appID.setText(String.valueOf(appointments.getAppointmentId()));
        title.setText(appointments.getTitle());
        description.setText(appointments.getDescription());
        location.setText(appointments.getLocation());

        // Select the matching value in the type ComboBox
        for (Appointments option : type.getItems()) {
            if (option.getType().equals(appointments.getType())) {
                type.setValue(option);
                break;
            }
        }

        date.setValue(appointments.getStart().toLocalDate());
        time.setValue(appointments.getStart().toLocalTime());

        // Select the matching value in the cusID ComboBox
        for (Customers customer : cusID.getItems()) {
            if (customer.getCustomerId().equals(appointments.getCustomerId())) {
                cusID.setValue(customer);
                break;
            }
        }

        // Select the matching value in the userID ComboBox
        for (Users user : userID.getItems()) {
            if ((user.getUserId()).equals(String.valueOf(appointments.getUserId()))) {
                userID.setValue(user);
                break;
            }
        }

        // Select the matching value in the contacts ComboBox
        for (Contacts contact : contacts.getItems()) {
            if (contact.toString().equals(appointments.getContactId())) {
                contacts.setValue(contact);
                break;
            }
        }
    }

    @FXML
    void onActionDeleteApp (ActionEvent event) throws IOException, SQLException {
        Appointments selectedAppointment = appointments.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Selected appointment will be deleted, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            int appointmentId = Integer.parseInt(String.valueOf((selectedAppointment.getAppointmentId())));
            AppointmentQuery.delete(appointmentId);

            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Appointment Deleted");
            confirmationAlert.setHeaderText(null);
            String appointmentType = selectedAppointment.getType();
            confirmationAlert.setContentText("Appointment ID: " + appointmentId + "\nAppointment Type: " + appointmentType + "\n\nAppointment deleted successfully!");
            confirmationAlert.showAndWait();
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionBack2 (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private void populatecontactscomboBox() {
        try {
            contactsList = ContactsQuery.select();
            contacts.setItems(contactsList);
        } catch (SQLException e) {
        }
    }

    private void populatecustomeridcombobox() {
        try {
            customerList = CustomerQuery.select();
            cusID.setItems(customerList);
        } catch (SQLException e) {

        }
    }

    private void populateusercombobox() {
        try {
            userList = LoginQuery.select2();
            userID.setItems(userList);
        } catch (SQLException e) {

        }
    }

    private void populatetypecombobox() {
        try {
            typeList = AppointmentQuery.select();
            type.setItems(typeList);
        } catch (SQLException e) {

        }
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

        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(22,0);

        while (start.isBefore(end.plusSeconds(1))) {
            time.getItems().add(start);
            start = start.plusHours(1);
        }
        time.getSelectionModel().getSelectedItem();

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

        ObservableList<Appointments> filteredByMonthList = appointmentsList.filtered(appointment -> appointment.getStart().getMonth() == LocalDate.now().getMonth());
        appointments2.setItems(filteredByMonthList);

        contactidcol1.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        customeridcol1.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appidcol1.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        useridcol1.setCellValueFactory(new PropertyValueFactory<>("userId"));
        titlecol1.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptioncol1.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationcol1.setCellValueFactory(new PropertyValueFactory<>("location"));
        typecol1.setCellValueFactory(new PropertyValueFactory<>("type"));
        startcol1.setCellValueFactory(new PropertyValueFactory<>("start"));
        endcol1.setCellValueFactory(new PropertyValueFactory<>("end"));

        ObservableList<Appointments> filteredByWeekList = appointmentsList.filtered(appointment -> {
            LocalDate today = LocalDate.now();
            LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
            LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
            LocalDateTime appointmentStart = appointment.getStart();
            LocalDate appointmentStartDate = appointmentStart.toLocalDate();
            return appointmentStartDate.isAfter(startOfWeek) && appointmentStartDate.isBefore(endOfWeek);
        });
        appointments3.setItems(filteredByWeekList);

        contactidcol11.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        customeridcol11.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appidcol11.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        useridcol11.setCellValueFactory(new PropertyValueFactory<>("userId"));
        titlecol11.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptioncol11.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationcol11.setCellValueFactory(new PropertyValueFactory<>("location"));
        typecol11.setCellValueFactory(new PropertyValueFactory<>("type"));
        startcol11.setCellValueFactory(new PropertyValueFactory<>("start"));
        endcol11.setCellValueFactory(new PropertyValueFactory<>("end"));

        populatecontactscomboBox();
        populatecustomeridcombobox();
        populateusercombobox();
        populatetypecombobox();

    }


}
