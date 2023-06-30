package com.example.test2;

import com.example.test2.dao.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

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




        stage.setOnCloseRequest(event -> {
            JDBC.closeConnection();
        });
    }




    public static void main(String[] args) {
        //Locale.setDefault(Locale.FRENCH);
        //TimeZone.setDefault(TimeZone.getTimeZone("Canada/Central"));
        rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
        }
        //System.out.println(ZoneId.systemDefault());
        //ZoneId.getAvailableZoneIds().stream().filter(z->z.contains("Canada")).sorted().forEach(System.out::println);

        /*LocalDate myLD = LocalDate.of(2023, 06,20);
        LocalTime myLT = LocalTime.of(07,30);
        LocalDateTime myLDT = LocalDateTime.of(myLD, myLT);
        ZoneId myZoneID = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneID);
        System.out.println(myZDT);*/
        launch();
    }

}