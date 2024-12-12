package com.example.demo.gui;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ShieldImage.
 * <p>
 * This class contains unit tests for the ShieldImage class, which manages the display and visibility
 * of the shield image in the game.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/test/java/com/example/demo/gui/ShieldImageTest.java">Source code</a>
 */
class ShieldImageTest extends ApplicationTest {

    /**
     * The ShieldImage instance used for testing.
     * <p>
     * This field holds the ShieldImage instance, which manages the display and visibility of the shield image in the game.
     */
    private ShieldImage shieldImage;

    /**
     * Initializes the JavaFX platform for testing.
     */
    @BeforeAll
    public static void initJFX() {
        Platform.startup(() -> {});
    }

    /**
     * Sets up the test environment before each test.
     * <p>
     * This method initializes the ShieldImage instance with specific layout coordinates.
     */
    @BeforeEach
    void setUp() {
        shieldImage = new ShieldImage(100, 200);
    }

    /**
     * Tests the initialization of the ShieldImage.
     * <p>
     * This test verifies that the ShieldImage is correctly initialized with the specified layout coordinates,
     * dimensions, visibility, and image.
     */
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

    /**
     * Tests the visibility of the ShieldImage when shown.
     * <p>
     * This test verifies that the ShieldImage becomes visible when the showShield method is called.
     */
    @Test
    void testShowShield() {
        shieldImage.showShield();
        assertTrue(shieldImage.isVisible());
    }

    /**
     * Tests the visibility of the ShieldImage when hidden.
     * <p>
     * This test verifies that the ShieldImage becomes invisible when the hideShield method is called.
     */
    @Test
    void testHideShield() {
        shieldImage.showShield();
        shieldImage.hideShield();
        assertFalse(shieldImage.isVisible());
    }
}