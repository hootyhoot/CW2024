package com.example.demo.gui;

import javafx.stage.Stage;

/**
 * Represents a window in the application.
 * Manages the creation and retrieval of the window stage.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/gui/Window.java">Source code</a>
 */
public class Window {
    /**
     * The singleton instance of the Window.
     */
    private static Window m_Window;

    /**
     * The stage for the window.
     */
    private final Stage m_Stage;

    /**
     * Constructs a Window with a new stage.
     */
    private Window() {
        m_Stage = new Stage();
    }

    /**
     * Returns the Window instance, creating it if necessary.
     *
     * @return the Window instance
     */
    public static Window getWindow() {
        if (m_Window == null) {
            m_Window = new Window();
        }
        return m_Window;
    }

    /**
     * Returns the stage associated with this window.
     *
     * @return the stage
     */
    public Stage getStage() {
        return m_Stage;
    }
}