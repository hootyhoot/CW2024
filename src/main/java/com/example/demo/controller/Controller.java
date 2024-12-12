package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.levels.LevelParent;

/**
 * Controller class that manages the game flow and transitions between levels.
 * Implements the Observer interface to respond to level changes.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/controller/Controller.java">Source code</a>
 */
public class Controller implements Observer {

	/**
	 * The fully qualified name of the first level class.
	 */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";

	/**
	 * The primary stage for the application.
	 */
	private final Stage m_Stage;

	/**
	 * Constructs a Controller with the specified Stage.
	 *
	 * @param stage the primary stage for the application
	 */
	public Controller(Stage stage) {
		this.m_Stage = stage;
	}

	/**
	 * Launches the game by showing the stage and starting the first level.
	 *
	 * @throws ClassNotFoundException if the LevelOne class cannot be found
	 * @throws NoSuchMethodException if the constructor for LevelOne cannot be found
	 * @throws SecurityException if there is a security violation
	 * @throws InstantiationException if the LevelOne class cannot be instantiated
	 * @throws IllegalAccessException if the constructor for LevelOne is not accessible
	 * @throws IllegalArgumentException if the arguments for the constructor are invalid
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		m_Stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Updates the controller in response to changes in the observed object.
	 *
	 * @param arg0 the observable object
	 * @param arg1 an argument passed to the notifyObservers method
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

	/**
	 * Transitions to the specified level by class name.
	 *
	 * @param className the fully qualified name of the level class
	 * @throws ClassNotFoundException if the specified class cannot be found
	 * @throws NoSuchMethodException if the constructor for the specified class cannot be found
	 * @throws SecurityException if there is a security violation
	 * @throws InstantiationException if the specified class cannot be instantiated
	 * @throws IllegalAccessException if the constructor for the specified class is not accessible
	 * @throws IllegalArgumentException if the arguments for the constructor are invalid
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(m_Stage.getHeight(), m_Stage.getWidth());
		myLevel.addObserver(this);
		Scene scene = myLevel.initializeScene();
		m_Stage.setScene(scene);
		myLevel.startGame();
	}
}