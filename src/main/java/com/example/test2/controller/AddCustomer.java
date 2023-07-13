package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.dao.CustomerQuery;
import com.example.test2.dao.FLDQuery;
import com.example.test2.model.Customers;
import com.example.test2.model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

/**This is the controller for the form to add customers*/
public class AddCustomer implements Initializable {
    @FXML
    private TextField cusname;
    @FXML
    private TextField cusid;
    @FXML
    private TextField cusaddress;
    @FXML
    private TextField cuspostcode;
    @FXML
    private TextField cusphone;
    @FXML
    private ComboBox<String> cuscountry;
    @FXML
    private ComboBox<FirstLevelDivisions> cusdivision;
    @FXML
    private Button submit;
    @FXML
    private Button back;
    public ObservableList<FirstLevelDivisions> divisionNames = FXCollections.observableArrayList();
    Stage stage;
    Parent scene;

    /**this populates the division combobox with division names using the select2() method from the corresponding query class*/
    private void populatedivisionComboBox() {
        try {
            divisionNames = FLDQuery.select2();
            cusdivision.setItems(divisionNames);
        } catch (SQLException e) {
        }
    }

    /**this filters the divisions based on the country selected in the combobox*/
    private ObservableList<FirstLevelDivisions> filterDivisionsByCountry(String country, ObservableList<FirstLevelDivisions> divisionList) {
        ObservableList<FirstLevelDivisions> filteredDivisions = FXCollections.observableArrayList();

        for (FirstLevelDivisions division : divisionList) {
            if (country.equals("U.S") && isDivisionInUnitedStates(division)) {
                filteredDivisions.add(division);
            } else if (country.equals("Canada") && isDivisionInCanada(division)) {
                filteredDivisions.add(division);
            } else if (country.equals("UK") && isDivisionInUnitedKingdom(division)) {
                filteredDivisions.add(division);
            }
        }

        return filteredDivisions;
    }

    /**this checks if the division matches the country selected, in this instance the U.S.*/
    private boolean isDivisionInUnitedStates(FirstLevelDivisions division) {
        int selectedCountryId = getCountryIdByName(cuscountry.getSelectionModel().getSelectedItem());
        return division.getCountryId() == selectedCountryId;
    }

    /**this checks if the division matches the country selected, in this instance Canada*/
    private boolean isDivisionInCanada(FirstLevelDivisions division) {
        int selectedCountryId = getCountryIdByName(cuscountry.getSelectionModel().getSelectedItem());
        return division.getCountryId() == selectedCountryId;
    }

    /**this checks if the division matches the country selected, in this instance the UK*/
    private boolean isDivisionInUnitedKingdom(FirstLevelDivisions division) {
        int selectedCountryId = getCountryIdByName(cuscountry.getSelectionModel().getSelectedItem());
        return division.getCountryId() == selectedCountryId;
    }

    /**this hashmap maps the country abbreviations to the country ID so the country name can be displayed in the combobox
     * while maintaining the correct ID*/
    private int getCountryIdByName(String countryName) {
        HashMap<String, Integer> countryIdMap = new HashMap<>();
        countryIdMap.put("U.S", 1);
        countryIdMap.put("UK", 2);
        countryIdMap.put("Canada", 3);

        return countryIdMap.getOrDefault(countryName, -1);
    }

    /**this is the event handler to submit the add customer form, it utilizes the insert() method from the corresponding query class
     * and provides an error message if there is anything wrong with the form*/
    @FXML
    void onActionSubmit(ActionEvent event) throws IOException {
        try {
            String customerName = cusname.getText();
            String address = cusaddress.getText();
            String postalCode = cuspostcode.getText();
            String phone = cusphone.getText();
            int divisionId = cusdivision.getValue().getDivisionId();

            CustomerQuery.Insertcus(customerName, address, postalCode, phone, divisionId);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter correct values.");
            alert.showAndWait();
        }
    }

    /**this is the event handler to submit updates using the add customer form, it utilizes the update() method from the corresponding query class
     * and provides an error message if there is anything wrong with the form*/
    @FXML
    void onActionUpdate (ActionEvent event) {
        try {
            String customerId = cusid.getText();
            String customerName = cusname.getText();
            String address = cusaddress.getText();
            String postalCode = cuspostcode.getText();
            String phone = cusphone.getText();
            int divisionId = cusdivision.getValue().getDivisionId();

            CustomerQuery.update(Integer.parseInt(customerId), customerName, address, postalCode, phone, divisionId);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (RuntimeException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter correct values.");
            alert.showAndWait();
        }
    }

    /**this is the event handler to go back to the previous page*/
    @FXML
    void onActionBack (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**this collects customer information to be updated and sent to the add customer form*/
    public void sendCustomer (Customers customers) {
        cusid.setText(customers.getCustomerId());
        cusname.setText(customers.getCustomerName());
        cusaddress.setText(customers.getAddress());
        cuspostcode.setText(customers.getPostalCode());
        cusphone.setText(customers.getPhone());
    }

    /**this is the initialize method, it sets a list of countries to the corresponding combobox, calls the method to populate the division combobox
     * and sets a listener to the countries combobox using a lambda expression*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> countries = FXCollections.observableArrayList();
        countries.add("U.S");
        countries.add("UK");
        countries.add("Canada");

        cuscountry.setItems(countries);

        populatedivisionComboBox();

        cuscountry.setOnAction(event -> {
            String selectedCountry = cuscountry.getSelectionModel().getSelectedItem();
            ObservableList<FirstLevelDivisions> filteredDivisions = filterDivisionsByCountry(selectedCountry, divisionNames);
            cusdivision.setItems(filteredDivisions);
        });

    }


}
