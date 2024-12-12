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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the main menu of the GUI.
 * Manages the initialization and display of the main menu, including background and buttons.
 */
public class MainMenu {
    /**
     * The logger for the MainMenu class.
     */
    private static final Logger LOGGER = Logger.getLogger(MainMenu.class.getName());

    /**
     * The gap size between grid elements in the main menu.
     */
    private static final int GRID_GAP_SIZE = 50;

    /**
     * The height of each row in the grid.
     */
    private static final int ROW_HEIGHT = 50;

    /**
     * The singleton instance of the MainMenu.
     */
    private static MainMenu m_MainMenu;

    /**
     * The width of the screen.
     */
    private static final int SCREEN_WIDTH = 1300;

    /**
     * The height of the screen.
     */
    private static final int SCREEN_HEIGHT = 750;

    /**
     * The X position of the start button.
     */
    private static final int START_BUTTON_X = 100;

    /**
     * The Y position of the start button.
     */
    private static final int START_BUTTON_Y = 250;

    /**
     * The X position of the exit button.
     */
    private static final int EXIT_BUTTON_X = 100;

    /**
     * The Y position of the exit button.
     */
    private static final int EXIT_BUTTON_Y = 250;

    /**
     * The title of the main menu window.
     */
    private static final String TITLE = "Sky Invaders";

    /**
     * The root group for the main menu.
     */
    private final Group m_Root;

    /**
     * The stage for the main menu.
     */
    private final Stage m_Stage;

    /**
     * Constructs a MainMenu with the specified stage.
     *
     * @param stage the stage for the main menu
     */
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

    /**
     * Returns the MainMenu instance, creating it if necessary.
     *
     * @param stage the stage for the main menu
     * @return the MainMenu instance
     */
    public static MainMenu getMenu(Stage stage) {
        if (m_MainMenu == null) {
            m_MainMenu = new MainMenu(stage);
        }
        return m_MainMenu;
    }

    public Group getRoot() {
        return m_Root;
    }

    /**
     * Initializes the main menu with background and buttons.
     */
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
                LOGGER.log(Level.SEVERE, "Failed to start game", e);
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

    /**
     * Starts the game by clearing the root and launching the game controller.
     *
     * @throws ClassNotFoundException if the class is not found
     * @throws InvocationTargetException if an invocation target exception occurs
     * @throws NoSuchMethodException if the method is not found
     * @throws InstantiationException if instantiation fails
     * @throws IllegalAccessException if access is illegal
     */
    public void startGame() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        m_Root.getChildren().clear();
        Controller controller = new Controller(m_Stage);
        controller.launchGame();
    }

    /**
     * Exits the game by terminating the application.
     */
    public void exitGame() {
        Platform.exit();
    }

    /**
     * Shows the main menu stage.
     */
    public void showMenu() {
        m_Stage.show();
    }
}