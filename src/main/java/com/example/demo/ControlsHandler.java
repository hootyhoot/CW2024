package com.example.demo;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControlsHandler {
   private final LevelParent m_LevelParent;

    public ControlsHandler(LevelParent levelParent) {
        this.m_LevelParent = levelParent;
    }
    protected void createControlsListeners(ImageView background, UserPlane user, Timeline timeline) {
        background.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) user.moveUp();
                if (kc == KeyCode.DOWN) user.moveDown();
                if (kc == KeyCode.RIGHT) user.moveRight();
                if (kc == KeyCode.LEFT) user.moveLeft();
                if (kc == KeyCode.SPACE) m_LevelParent.fireProjectile();
                if (kc == KeyCode.ESCAPE) timeline.pause();
                if (kc == KeyCode.ENTER) timeline.play();
            }
        });
        background.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stopVertically();
                if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) user.stopHorizontally();
            }
        });
    }
}
