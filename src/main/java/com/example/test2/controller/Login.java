package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.helper.LoginQuery;
import com.example.test2.model.Customers;
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
import java.time.ZoneId;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public Label lblusername;
    public Label lblpassword;
    public Button btnlogin;
    public Label location;
    Stage stage;
    Parent scene;
    @FXML
    public Button login;
    @FXML
    public TextField usernameid;
    @FXML
    public PasswordField passwordid;
    @FXML
    public TextField locationTextField;

    @FXML
    void onActionLogin (ActionEvent event) throws IOException {

        String username = usernameid.getText();
        String password = passwordid.getText();

        try {
            boolean isAuthenticated = LoginQuery.selectCredentials(username, password);

            if (isAuthenticated) {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                showErrorDialog("error.invalid_credentials", "error.title");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String determineUserLocation() {
        ZoneId userZoneID = ZoneId.systemDefault();
        return userZoneID.toString();
    }

    private void showErrorDialog(String key, String key2) {
        ResourceBundle rs = ResourceBundle.getBundle("main/Nat");
        String errorMessage = rs.getString(key);
        String errorTitle = rs.getString(key2);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorTitle);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblusername.setText(HelloApplication.rb.getString("username"));
        lblpassword.setText(HelloApplication.rb.getString("password"));
        btnlogin.setText(HelloApplication.rb.getString("login"));

        String userLocation = determineUserLocation();
        location.setText(userLocation);
    }


}
