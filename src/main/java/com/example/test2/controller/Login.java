package com.example.test2.controller;

import com.example.test2.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public Label lblusername;
    public Label lblpassword;
    public Button btnlogin;
    Stage stage;
    Parent scene;
    @FXML
    public Button login;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public TextField locationTextField;

    @FXML
    void onActionLogin (ActionEvent event) throws IOException {


        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblusername.setText(HelloApplication.rb.getString("username"));
        lblpassword.setText(HelloApplication.rb.getString("password"));
        btnlogin.setText(HelloApplication.rb.getString("login"));
    }
}
