package com.example.demo.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the end menu in the game.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/gui/EndMenu.java">Source code</a>
 */
public class EndMenu {
    /**
     * The logger for the EndMenu class.
     */
    private static final Logger LOGGER = Logger.getLogger(EndMenu.class.getName());

    /**
     * The gap size between grid elements in the end menu.
     */
    private static final int GRID_GAP_SIZE = 50;

    /**
     * The height of each row in the grid.
     */
    private static final int ROW_HEIGHT = 50;

    /**
     * The background image of the end menu.
     */
    private MenuImage m_BackgroundImage;

    /**
     * The restart button in the end menu.
     */
    private ButtonImage m_RestartButton;

    /**
     * The width of the screen.
     */
    private static final int SCREEN_WIDTH = 1300;

    /**
     * The height of the screen.
     */
    private static final int SCREEN_HEIGHT = 750;

    /**
     * The X position of the restart button.
     */
    private static final int RESTART_BUTTON_X = 100;

    /**
     * The Y position of the restart button.
     */
    private static final int RESTART_BUTTON_Y = 250;

    /**
     * The X position of the exit button.
     */
    private static final int EXIT_BUTTON_X = 100;

    /**
     * The Y position of the exit button.
     */
    private static final int EXIT_BUTTON_Y = 250;

    /**
     * The X position of the win or lose image.
     */
    private static final int WIN_OR_LOSE_IMAGE_X = 150;

    /**
     * The Y position of the win or lose image.
     */
    private static final int WIN_OR_LOSE_IMAGE_Y = 250;

    /**
     * The singleton instance of the EndMenu.
     */
    private static EndMenu m_EndMenu;

    /**
     * Constructs an EndMenu with the specified root and win/lose status.
     *
     * @param root the root group
     * @param winOrLose the win or lose status
     */
    private EndMenu(Group root, boolean winOrLose) {
        StackPane stack = new StackPane();
        GridPane grid = new GridPane();
        m_BackgroundImage = new MenuImage(0, 0, "/com/example/demo/images/menuBackground.jpg", SCREEN_HEIGHT, SCREEN_WIDTH);
        m_RestartButton = new ButtonImage("/com/example/demo/images/restartButton.png", RESTART_BUTTON_X, RESTART_BUTTON_Y);
        ButtonImage exitButton = new ButtonImage("/com/example/demo/images/exitButton.png", EXIT_BUTTON_X, EXIT_BUTTON_Y);
        ButtonImage winOrLoseImage = new ButtonImage(winOrLose ? "/com/example/demo/images/winImage.png" : "/com/example/demo/images/loseImage.png", WIN_OR_LOSE_IMAGE_X, WIN_OR_LOSE_IMAGE_Y);
        m_RestartButton.setOnMouseClicked(event -> {
            try {
                startGame(root);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to start game", e);
            }
        });
        exitButton.setOnMouseClicked(event -> exitGame());

        grid.setAlignment(Pos.CENTER);
        grid.add(winOrLoseImage, 0, 0);
        grid.add(m_RestartButton, 0, 2);
        grid.add(exitButton, 0, 4);

        grid.setVgap(GRID_GAP_SIZE);
        grid.getRowConstraints().add(new RowConstraints(ROW_HEIGHT));
        grid.getRowConstraints().add(new RowConstraints(ROW_HEIGHT));
        grid.getRowConstraints().add(new RowConstraints(ROW_HEIGHT-20));
        stack.getChildren().addAll(m_BackgroundImage, grid);
        root.getChildren().add(stack);
    }

    /**
     * Displays the end menu.
     *
     * @param root the root group
     * @param winOrLose the win or lose status
     */
    public static void showEndMenu(Group root, boolean winOrLose) {
        if (m_EndMenu == null) {
            System.gc();
            m_EndMenu = new EndMenu(root, winOrLose);
        }
    }

    /**
     * Starts the game.
     *
     * @param root the root group
     * @throws ClassNotFoundException if the class is not found
     * @throws InvocationTargetException if an invocation target exception occurs
     * @throws NoSuchMethodException if the method is not found
     * @throws InstantiationException if instantiation fails
     * @throws IllegalAccessException if access is illegal
     */
    public void startGame(Group root) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        m_EndMenu = null;
        root.getChildren().clear();
        m_BackgroundImage = null;
        m_RestartButton = null;
        MainMenu Restart = MainMenu.getMenu(Window.getWindow().getStage());
        Restart.startGame();
    }

    /**
     * Exits the game.
     */
    public void exitGame() {
        Platform.exit();
    }
}