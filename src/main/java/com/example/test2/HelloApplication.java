package com.example.test2;

import com.example.test2.dao.JDBC;
import com.example.test2.model.Appointments;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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

        //filename variable
        String fileName = "groceries.txt", item;

        //create scanner object
        Scanner keyBoard = new Scanner(System.in);

        //get item count
        System.out.println("How many items do you have?");
        int numItems = keyBoard.nextInt();
        //clear keyboard buffer
        keyBoard.nextLine();
        //create and open file
        PrintWriter outputFile = new PrintWriter(fileName);

        for (int i=0; i < numItems; i++) {
            System.out.println("Enter item" + (i+1) + ": ");
            item = keyBoard.nextLine();
            outputFile.println(item);
        }

        //close file
        outputFile.close();
        System.out.println("File written");

        launch();
    }

}