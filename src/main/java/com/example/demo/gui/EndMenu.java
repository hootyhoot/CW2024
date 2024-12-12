package com.example.demo.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import java.lang.reflect.InvocationTargetException;

public class EndMenu {
    public static final int GRID_GAP_SIZE = 50;
    public static final int ROW_HEIGHT = 50;
    private final Group root;
    private MenuImage m_BackgroundImage;
    private ButtonImage m_RestartButton;
    private ButtonImage m_ExitButton;
    private ButtonImage m_WinOrLoseImage;
    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final int RESTART_BUTTON_X = 100;
    private static final int RESTART_BUTTON_Y = 250;
    private static final int EXIT_BUTTON_X = 100;
    private static final int EXIT_BUTTON_Y = 250;
    private static final int WIN_OR_LOSE_IMAGE_X = 150;
    private static final int WIN_OR_LOSE_IMAGE_Y = 250;
    private static EndMenu m_EndMenu;

    private EndMenu(Group root, boolean winOrLose) {
        this.root = root;
        StackPane stack = new StackPane();
        GridPane grid = new GridPane();
        m_BackgroundImage = new MenuImage(0, 0, "/com/example/demo/images/menuBackground.jpg", SCREEN_HEIGHT, SCREEN_WIDTH);
        m_RestartButton = new ButtonImage("/com/example/demo/images/restartButton.png", RESTART_BUTTON_X, RESTART_BUTTON_Y);
        m_ExitButton = new ButtonImage("/com/example/demo/images/exitButton.png", EXIT_BUTTON_X, EXIT_BUTTON_Y);
        m_WinOrLoseImage = new ButtonImage(winOrLose ? "/com/example/demo/images/winImage.png" : "/com/example/demo/images/loseImage.png", WIN_OR_LOSE_IMAGE_X, WIN_OR_LOSE_IMAGE_Y);
        m_RestartButton.setOnMouseClicked(event -> {
            try {
                startGame(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        m_ExitButton.setOnMouseClicked(event -> exitGame());

        grid.setAlignment(Pos.CENTER);
        grid.add(m_WinOrLoseImage, 0, 0);
        grid.add(m_RestartButton, 0, 2);
        grid.add(m_ExitButton, 0, 4);

        grid.setVgap(GRID_GAP_SIZE);
        grid.getRowConstraints().add(new RowConstraints(ROW_HEIGHT));
        grid.getRowConstraints().add(new RowConstraints(ROW_HEIGHT));
        grid.getRowConstraints().add(new RowConstraints(ROW_HEIGHT-20));
        stack.getChildren().addAll(m_BackgroundImage, grid);
        root.getChildren().add(stack);
    }

    public static void showEndMenu(Group root, boolean winOrLose) {
        if (m_EndMenu == null) {
            System.gc();
            m_EndMenu = new EndMenu(root, winOrLose);
        }
    }

    public void startGame(Group root) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        m_EndMenu = null;
        root.getChildren().clear();
        m_BackgroundImage = null;
        m_RestartButton = null;
        MainMenu Restart = MainMenu.getMenu(Window.getWindow().getStage());
        Restart.startGame();
    }

    public void exitGame() {
        Platform.exit();
    }
}