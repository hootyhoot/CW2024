package com.example.demo.gui;

import javafx.stage.Stage;

public class Window {
    private static Window m_Instance;
    private final Stage m_Stage;

    private Window() {
        m_Stage = new Stage();
    }

    public static Window getInstance() {
        if (m_Instance == null) {
            m_Instance = new Window();
        }
        return m_Instance;
    }

    public Stage getStage() {
        return m_Stage;
    }
}