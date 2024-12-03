module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    opens com.example.demo.entities to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.handlers to javafx.fxml;
    opens com.example.demo.gui to javafx.fxml;
}