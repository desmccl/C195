package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.helper.CustomerQuery;
import com.example.test2.helper.FLDQuery;
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

//import static com.example.test2.helper.CustomerQuery.insertcus;


public class AddCustomer implements Initializable {

    public TextField cusname;
    public TextField cusid;
    public TextField cusaddress;
    public TextField cuspostcode;
    public TextField cusphone;
    public ComboBox<String> cuscountry;
    @FXML
    private ComboBox<FirstLevelDivisions> cusdivision;
    public Button submit;
    public Button back;
    public ObservableList<FirstLevelDivisions> divisionNames = FXCollections.observableArrayList();
    public ObservableList<Customers> customerList = FXCollections.observableArrayList();



    Stage stage;
    Parent scene;

    private void populatedivisionComboBox() {
        try {
            divisionNames = FLDQuery.select2();
            cusdivision.setItems(divisionNames);
        } catch (SQLException e) {
        }
    }

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

    private boolean isDivisionInUnitedStates(FirstLevelDivisions division) {
        int selectedCountryId = getCountryIdByName(cuscountry.getSelectionModel().getSelectedItem());
        return division.getCountryId() == selectedCountryId;
    }

    private boolean isDivisionInCanada(FirstLevelDivisions division) {
        int selectedCountryId = getCountryIdByName(cuscountry.getSelectionModel().getSelectedItem());
        return division.getCountryId() == selectedCountryId;
    }

    private boolean isDivisionInUnitedKingdom(FirstLevelDivisions division) {
        int selectedCountryId = getCountryIdByName(cuscountry.getSelectionModel().getSelectedItem());
        return division.getCountryId() == selectedCountryId;
    }

    private int getCountryIdByName(String countryName) {
        HashMap<String, Integer> countryIdMap = new HashMap<>();
        countryIdMap.put("U.S", 1);
        countryIdMap.put("UK", 2);
        countryIdMap.put("Canada", 3);

        return countryIdMap.getOrDefault(countryName, -1);
    }

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

    @FXML
    void onActionBack (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void sendCustomer (Customers customers) {
        cusid.setText(customers.getCustomerId());
        cusname.setText(customers.getCustomerName());
        cusaddress.setText(customers.getAddress());
        cuspostcode.setText(customers.getPostalCode());
        cusphone.setText(customers.getPhone());
    }

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
