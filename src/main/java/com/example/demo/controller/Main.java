package com.example.demo.controller;

import com.example.demo.gui.MainMenu;
import com.example.demo.gui.Window;
import javafx.application.Application;

import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage stage) throws  SecurityException, IllegalArgumentException {
		Window window = Window.getInstance();
		stage = window.getStage();
		MainMenu mainMenu = MainMenu.getMenu(stage);
		mainMenu.initialiseMenu();
		mainMenu.showMenu();
		stage.toFront();
	}

	public static void main(String[] args) {
		launch();
	}
}