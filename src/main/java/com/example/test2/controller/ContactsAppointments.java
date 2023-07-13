package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.dao.AppointmentQuery;
import com.example.test2.dao.ContactsQuery;
import com.example.test2.model.Appointments;
import com.example.test2.model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**this is the controller for the schedule for each contact in the organization that includes appointment ID, title, type and description,
 * start date and time, end date and time, and customer ID*/
public class ContactsAppointments implements Initializable {
    @FXML
    private TableColumn <Appointments, Integer> cusid2;
    @FXML
    private TableColumn <Appointments, Integer> appid2;
    @FXML
    private TableColumn <Appointments, Integer> userid2;
    @FXML
    private TableColumn <Appointments, String> title2;
    @FXML
    private TableColumn <Appointments, String> description2;
    @FXML
    private TableColumn <Appointments, String> location2;
    @FXML
    private TableColumn <Appointments, String> type2;
    @FXML
    private TableColumn <Appointments, Timestamp> date2;
    @FXML
    private TableColumn <Appointments, Timestamp> time2;
    @FXML
    private Button backcontact;
    @FXML
    private TableColumn <Appointments, Integer> contact2;
    @FXML
    private TableView <Appointments> appointments4;
    @FXML
    private ComboBox <Contacts> contactsapp;
    private int contactId;
    Stage stage;
    Parent scene;
    public ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
    public ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

    /**this is the event handler to go back to the previous page*/
    @FXML
    void onActionBackContact (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**this populates the contacts combobox utilizing the select() method from the corresponding query class*/
    private void populatecontactscomboBox() {
        try {
            contactsList = ContactsQuery.select();
            contactsapp.setItems(contactsList);
        } catch (SQLException e) {
        }
    }

    /**this sets the items in the table for the selected contact using the selectAppointmentsForContact() method in the corresponding query class*/
    private void updateAppointmentsTableView(int contactId) {
        try {
            appointmentsList = AppointmentQuery.selectAppointmentsForContact(contactId);
            appointments4.setItems(appointmentsList);
        } catch (SQLException e) {
        }
    }

    /**this is the initialize method, it sets up the tableview, calls the method to popualte the contacts combobox, and
     * sets items to the tableview using a filtered list that is generated from the listener on the combobox,
     * the listener is set using a lambda expression*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contact2.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        cusid2.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appid2.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        userid2.setCellValueFactory(new PropertyValueFactory<>("userId"));
        title2.setCellValueFactory(new PropertyValueFactory<>("title"));
        description2.setCellValueFactory(new PropertyValueFactory<>("description"));
        location2.setCellValueFactory(new PropertyValueFactory<>("location"));
        type2.setCellValueFactory(new PropertyValueFactory<>("type"));
        date2.setCellValueFactory(new PropertyValueFactory<>("start"));
        time2.setCellValueFactory(new PropertyValueFactory<>("end"));

        populatecontactscomboBox();

        contactsapp.setOnAction(event -> {
            Contacts selectedContact = contactsapp.getSelectionModel().getSelectedItem();
            updateAppointmentsTableView(selectedContact.getContactId());
        });

        try {
            appointmentsList = AppointmentQuery.selectAppointmentsForContact(contactId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        appointments4.setItems(appointmentsList);
    }
}

