package com.example.demo.gui;

import javafx.stage.Stage;

public class Window {
    private static Window m_Window;
    private final Stage m_Stage;

    private Window() {
        m_Stage = new Stage();
    }

    public static Window getWindow() {
        if (m_Window == null) {
            m_Window = new Window();
        }
        return m_Window;
    }

    public Stage getStage() {
        return m_Stage;
    }
}