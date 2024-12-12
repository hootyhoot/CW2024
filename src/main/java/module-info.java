/**
 * Module definition for the com.example.demo application.
 * <p>
 * This module requires the following:
 * - javafx.controls: JavaFX controls module for building the GUI.
 * - javafx.fxml: JavaFX FXML module for loading FXML files.
 * - java.logging: Java logging module for logging purposes.
 * <p>
 * The module exports and opens the following packages:
 * - com.example.demo.controller: Exports the controller package.
 * - com.example.demo.entities: Opens the entities package to javafx.fxml.
 * - com.example.demo.levels: Opens the levels package to javafx.fxml.
 * - com.example.demo.handlers: Opens the handlers package to javafx.fxml.
 * - com.example.demo.gui: Opens the GUI package to javafx.fxml.
 */
module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    opens com.example.demo.entities to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.handlers to javafx.fxml;
    opens com.example.demo.gui to javafx.fxml;
}