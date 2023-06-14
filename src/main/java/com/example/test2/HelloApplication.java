package com.example.test2;

import com.example.test2.helper.FruitsQuery;
import com.example.test2.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Properties;

public class HelloApplication extends Application {
    public static ResourceBundle rb;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        JDBC.openConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Scheduler");
        stage.setScene(scene);
        stage.show();




        JDBC.closeConnection();
    }




    public static void main(String[] args) {
        //Locale.setDefault(Locale.FRENCH);
        rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());
        System.out.println(rb.getString("login"));
        launch();
    }

}