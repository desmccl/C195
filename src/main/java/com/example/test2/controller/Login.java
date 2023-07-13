package com.example.test2.controller;

import com.example.test2.HelloApplication;
import com.example.test2.dao.LoginQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**This is the controller class for the login page*/
public class Login implements Initializable {
    @FXML
    private Label lblusername;
    @FXML
    private Label lblpassword;
    @FXML
    private Button btnlogin;
    @FXML
    private Label location;
    @FXML
    private Button login;
    @FXML
    private TextField usernameid;
    @FXML
    private PasswordField passwordid;
    Stage stage;
    Parent scene;

    /**This is the event handler for the login button, it utilizes the selectCredentials() method from the corresponding query class
     * and checks to see if the username and password match any users in the database
     * it records every login attempt and stores it in a text file with the username, whether it was successful, and the date and time
     * it appends each attempt in the file so no data is lost*/
    @FXML
    void onActionLogin (ActionEvent event) throws IOException {

        String username = usernameid.getText();
        String password = passwordid.getText();

        try {
            boolean isAuthenticated = LoginQuery.selectCredentials(username, password);

            if (isAuthenticated) {
                recordLoginActivity(username, true);

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                recordLoginActivity(username, false);
                showErrorDialog("error.invalid_credentials", "error.title");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**this is the method to record login attempts utilized in the event handler for the login button*/
    private void recordLoginActivity(String username, boolean success) throws IOException {
        String fileName = "src/files/login_activity.txt";

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = now.format(formatter);

        // Record login activity with username, success status, and date/time
        String logEntry = "Username: " + username + ", Success: " + success + ", Date/Time: " + dateTime;

        // Create or append to the login activity file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(logEntry);
            writer.newLine();
        }
    }

    /**this determines the users location so it can be displayed*/
    private String determineUserLocation() {
        ZoneId userZoneID = ZoneId.systemDefault();
        return userZoneID.toString();
    }

    /**this creates and translates an error message in case the login attempt was not successful*/
    private void showErrorDialog(String key, String key2) {
        ResourceBundle rs = ResourceBundle.getBundle("main/Nat");
        String errorMessage = rs.getString(key);
        String errorTitle = rs.getString(key2);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorTitle);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }


/**This is the initialize method, it translates the text on the page to either french or english based on the users location
 * it also sets the text on the screen to show the users location utilizing the determineUserLocation() method*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblusername.setText(HelloApplication.rb.getString("username"));
        lblpassword.setText(HelloApplication.rb.getString("password"));
        btnlogin.setText(HelloApplication.rb.getString("login"));

        String userLocation = determineUserLocation();
        location.setText(userLocation);

    }


}
