package com.example.demo.handlers;

import com.example.demo.entities.UserPlane;
import com.example.demo.levels.LevelParent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Handles user controls for the game.
 */
public class ControlsHandler {
    /**
     * The level parent associated with this controls handler.
     */
    private final LevelParent m_LevelParent;

    /**
     * The set of pressed keys.
     */
    private final Set<KeyCode> pressedKeys = new HashSet<>();

    /**
     * Constructs a ControlsHandler with the specified level parent.
     *
     * @param levelParent the level parent
     */
    public ControlsHandler(LevelParent levelParent) {
        this.m_LevelParent = levelParent;
    }

    /**
     * Creates listeners for user controls.
     *
     * @param scene the scene to attach listeners to
     * @param user the user plane
     */
    public void createControlsListeners(Scene scene, UserPlane user) {
        scene.setOnKeyPressed(e -> {
            KeyCode kc = e.getCode();
            pressedKeys.add(kc);
            updateMovement(user);
            if (kc == KeyCode.SPACE) m_LevelParent.fireProjectile();
            if (kc == KeyCode.ESCAPE) m_LevelParent.pauseGame();
            if (kc == KeyCode.ENTER) m_LevelParent.resumeGame();
        });
        scene.setOnKeyReleased(e -> {
            KeyCode kc = e.getCode();
            pressedKeys.remove(kc);
            updateMovement(user);
        });
    }

    /**
     * Updates the movement of the user plane based on pressed keys.
     *
     * @param user the user plane
     */
    private void updateMovement(UserPlane user) {
        if (pressedKeys.contains(KeyCode.UP)) {
            user.moveUp();
        } else if (pressedKeys.contains(KeyCode.DOWN)) {
            user.moveDown();
        } else {
            user.stopVertically();
        }

        if (pressedKeys.contains(KeyCode.LEFT)) {
            user.moveLeft();
        } else if (pressedKeys.contains(KeyCode.RIGHT)) {
            user.moveRight();
        } else {
            user.stopHorizontally();
        }
    }
}