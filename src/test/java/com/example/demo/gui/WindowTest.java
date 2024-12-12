package com.example.demo.gui;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Window.
 * <p>
 * This class contains unit tests for the Window class, which manages the main application window
 * and ensures it follows the singleton pattern.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/test/java/com/example/demo/gui/WindowTest.java">Source code</a>
 */
class WindowTest extends ApplicationTest {

    /**
     * The Window instance used for testing.
     * <p>
     * This field holds the singleton instance of the Window class, which manages the main application window.
     */
    private Window window;

    /**
     * Sets up the test environment before each test.
     * <p>
     * This method initializes the Window instance using the singleton pattern.
     */
    @BeforeEach
    void setUp() {
        Platform.runLater(() -> window = Window.getWindow());
        waitForFxEvents();
    }

    /**
     * Tests the singleton instance of the Window.
     * <p>
     * This test verifies that the Window class correctly implements the singleton pattern by ensuring
     * that multiple calls to getWindow() return the same instance.
     */
    @Test
    void testSingletonInstance() {
        Platform.runLater(() -> {
            Window anotherWindow = Window.getWindow();
            assertSame(window, anotherWindow, "Window instances should be the same (singleton pattern)");
        });
        waitForFxEvents();
    }

    /**
     * Tests the initialization of the Stage.
     * <p>
     * This test verifies that the Stage is correctly initialized when the Window instance is created.
     */
    @Test
    void testStageInitialization() {
        Platform.runLater(() -> {
            Stage stage = window.getStage();
            assertNotNull(stage, "Stage should be initialized");
        });
        waitForFxEvents();
    }

    /**
     * Waits for JavaFX events to be processed.
     * <p>
     * This method ensures that JavaFX events are processed by sleeping the current thread for a short duration.
     */
    private void waitForFxEvents() {
        try {
            Thread.sleep(100); // Adjust the sleep time as needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}