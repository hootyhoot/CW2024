package com.example.demo.gui;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class WindowTest extends ApplicationTest {

    private Window window;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> window = Window.getWindow());
        waitForFxEvents();
    }

    @Test
    void testSingletonInstance() {
        Platform.runLater(() -> {
            Window anotherWindow = Window.getWindow();
            assertSame(window, anotherWindow, "Window instances should be the same (singleton pattern)");
        });
        waitForFxEvents();
    }

    @Test
    void testStageInitialization() {
        Platform.runLater(() -> {
            Stage stage = window.getStage();
            assertNotNull(stage, "Stage should be initialized");
        });
        waitForFxEvents();
    }

    private void waitForFxEvents() {
        try {
            Thread.sleep(100); // Adjust the sleep time as needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}