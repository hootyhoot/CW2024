package com.example.demo.gui;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class ShieldImageTest extends ApplicationTest {

    private ShieldImage shieldImage;

    @BeforeAll
    public static void initJFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        shieldImage = new ShieldImage(100, 200);
    }

    @Test
    void testShieldImageInitialization() {
        assertEquals(100, shieldImage.getLayoutX());
        assertEquals(200, shieldImage.getLayoutY());
        assertEquals(100, shieldImage.getFitHeight());
        assertEquals(100, shieldImage.getFitWidth());
        assertFalse(shieldImage.isVisible());
        assertNotNull(shieldImage.getImage());
        assertInstanceOf(Image.class, shieldImage.getImage());
    }

    @Test
    void testShowShield() {
        shieldImage.showShield();
        assertTrue(shieldImage.isVisible());
    }

    @Test
    void testHideShield() {
        shieldImage.showShield();
        shieldImage.hideShield();
        assertFalse(shieldImage.isVisible());
    }
}
