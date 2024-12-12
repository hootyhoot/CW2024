package com.example.demo.controller;

import com.example.demo.gui.MainMenu;
import com.example.demo.gui.Window;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class that serves as the entry point for the JavaFX application.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/controller/Main.java">Source code</a>
 */
public class Main extends Application {

	/**
	 * Default constructor for the Main class.
	 */
	public Main() {
		super();
	}

	/**
	 * Starts the JavaFX application by initializing and displaying the main menu.
	 *
	 * @param stage the primary stage for the application
	 * @throws SecurityException if there is a security violation
	 * @throws IllegalArgumentException if the arguments for the stage are invalid
	 */
	@Override
	public void start(Stage stage) throws SecurityException, IllegalArgumentException {
		Window window = Window.getWindow();
		stage = window.getStage();
		MainMenu mainMenu = MainMenu.getMenu(stage);
		mainMenu.initialiseMenu();
		mainMenu.showMenu();
		stage.toFront();
	}

	/**
	 * The main method that launches the JavaFX application.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch();
	}
}