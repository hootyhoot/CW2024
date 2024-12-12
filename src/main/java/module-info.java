/**
 * Module definition for the `com.example.demo` application.
 * <p>
 * This module requires the following:
 * - `javafx.controls`: JavaFX controls module for building the GUI.
 * - `javafx.fxml`: JavaFX FXML module for loading FXML files.
 * - `java.logging`: Java logging module for logging purposes.
 * - `org.junit.jupiter.api`: JUnit Jupiter API for unit testing.
 * - `org.testfx.junit5`: TestFX JUnit 5 for JavaFX testing.
 * - `org.junit.platform.commons`: JUnit Platform Commons for additional testing utilities.
 * <p>
 * The module exports and opens the following packages:
 * - `com.example.demo.controller`: Exports the controller package.
 * - `com.example.demo.entities`: Opens the entities package to `javafx.fxml`, `org.junit.jupiter.api`, and `org.junit.platform.commons`.
 * - `com.example.demo.levels`: Opens the levels package to `javafx.fxml`.
 * - `com.example.demo.handlers`: Opens the handlers package to `javafx.fxml`, `org.junit.jupiter.api`, and `org.junit.platform.commons`.
 * - `com.example.demo.gui`: Opens the GUI package to `javafx.fxml`, `org.junit.jupiter.api`, and `org.junit.platform.commons`.
 */
module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires org.junit.jupiter.api;
    requires org.testfx.junit5;
    requires org.junit.platform.commons;

    opens com.example.demo to javafx.fxml;
    opens com.example.demo.entities to javafx.fxml, org.junit.jupiter.api, org.junit.platform.commons;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.handlers to javafx.fxml, org.junit.jupiter.api, org.junit.platform.commons;
    opens com.example.demo.gui to javafx.fxml, org.junit.jupiter.api, org.junit.platform.commons;

    exports com.example.demo.controller;
}