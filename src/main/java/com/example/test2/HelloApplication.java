package com.example.test2;

import com.example.test2.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class HelloApplication extends Application {
    public static ResourceBundle rb;
    /**This is the start method, it opens the application to the login screen and opens and closes the connection to the database*/
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        JDBC.openConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("Scheduler");
        stage.setScene(scene);
        stage.show();


        stage.setOnCloseRequest(event -> {
            JDBC.closeConnection();
        });
    }



/**This is the main method, it checks the locale to translate the page and show the time zone*/
    public static void main(String[] args) throws IOException{
        //Locale.setDefault(Locale.FRENCH);
        //TimeZone.setDefault(TimeZone.getTimeZone("Canada/Central"));
        rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
        }

        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Runtime Version: " + System.getProperty("java.runtime.version"));

        launch();
    }

}