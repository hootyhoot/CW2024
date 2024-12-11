package com.example.demo.handlers;

import com.example.demo.entities.UserPlane;
import com.example.demo.levels.LevelParent;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class ControlsHandler {
    private final LevelParent m_LevelParent;
    private final Set<KeyCode> pressedKeys = new HashSet<>();

    public ControlsHandler(LevelParent levelParent) {
        this.m_LevelParent = levelParent;
    }

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