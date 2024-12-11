package com.example.demo.gui;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class MainMenu {
    public static final int GRID_GAP_SIZE = 50;
    public static final int ROW_HEIGHT = 50;
    private static MainMenu m_MainMenu;
    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final int START_BUTTON_X = 100;
    private static final int START_BUTTON_Y = 250;
    private static final int EXIT_BUTTON_X = 100;
    private static final int EXIT_BUTTON_Y = 250;
    private static final String TITLE = "Sky Battle";
    private final Group m_Root;
    private final Stage m_Stage;

    private MainMenu(Stage stage) {
        Image logo = new Image(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/logo.png")).toExternalForm());
        stage.getIcons().add(logo);
        this.m_Stage = stage;
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        m_Root = new Group();
        Scene scene = new Scene(m_Root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
    }

    public static MainMenu getMenu(Stage stage) {
        if (m_MainMenu == null) {
            m_MainMenu = new MainMenu(stage);
        }
        return m_MainMenu;
    }

    public void initialiseMenu() {

        StackPane stack = new StackPane();
        GridPane grid = new GridPane();

        MenuImage background = new MenuImage(0, 0, "/com/example/demo/images/menuBackground.jpg", SCREEN_HEIGHT, SCREEN_WIDTH);

        ButtonImage startGameButton = new ButtonImage("/com/example/demo/images/startButton.png", START_BUTTON_X, START_BUTTON_Y);
        ButtonImage exitGameButton = new ButtonImage("/com/example/demo/images/exitButton.png", EXIT_BUTTON_X, EXIT_BUTTON_Y);
        startGameButton.setOnMouseClicked(event -> {
            try {
                startGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        exitGameButton.setOnMouseClicked(event -> exitGame());

        grid.setAlignment(Pos.CENTER);
        grid.add(startGameButton, 0, 0);
        grid.add(exitGameButton, 0, 2);
        grid.setVgap(GRID_GAP_SIZE);
        grid.getRowConstraints().add(new RowConstraints(ROW_HEIGHT));
        stack.getChildren().addAll(background, grid);
        m_Root.getChildren().add(stack);
    }

    public void startGame() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        m_Root.getChildren().clear();
        Controller controller = new Controller(m_Stage);
        controller.launchGame();
    }

    public void exitGame() {
        Platform.exit();
    }

    public void showMenu() {
        m_Stage.show();
    }
}
