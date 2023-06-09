package com.example.test2.controller;

import com.example.test2.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
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


}
