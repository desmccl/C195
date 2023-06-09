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

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        JDBC.openConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

        FruitsQuery.select(3);

        //Locale.setDefault(new Locale("fr"));

        JDBC.closeConnection();
    }



    public static void main(String[] args) {
        launch();
    }

}