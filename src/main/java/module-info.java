module com.example.test2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.test2 to javafx.fxml;
    exports com.example.test2;
    exports com.example.test2.controller;
    opens com.example.test2.controller to javafx.fxml;
    opens com.example.test2.model to javafx.base;

}