package com.example.demo.entities;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Boss.
 * <p>
 * This class contains unit tests for the Boss class, which represents the boss enemy in the game.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/test/java/com/example/demo/entities/BossTest.java">Source code</a>
 */
class BossTest extends ApplicationTest {

    /**
     * Tests the Boss taking damage.
     * <p>
     * This test verifies that the Boss's health is correctly reduced when it takes damage, considering the shield status.
     */
    @Test
    void takeDamage() {
        Boss boss = new Boss(0.1); // Pass a fire rate to the constructor
        int initialHealth = boss.getHealth();
        boss.updateEntity();
        boss.takeDamage();
        if (boss.getShieldImage().isVisible()) {
            assertEquals(initialHealth, boss.getHealth());
        } else {
            assertEquals(initialHealth - 1, boss.getHealth());
        }
    }

    /**
     * Tests the updating of the Boss's shield.
     * <p>
     * This test verifies that the Boss's shield is correctly updated and becomes invisible after a certain number of frames.
     */
    @Test
    void updateShield() {
        Boss boss = new Boss(0.1); // Pass a fire rate to the constructor
        boss.activateShield();
        int n = 0;
        while (boss.getShieldImage().isVisible()) {
            boss.updateEntity();
            n++;
        }
        assertTrue(n <= 60); // MAX_FRAMES_WITH_SHIELD is 60
        assertFalse(boss.getShieldImage().isVisible());
    }
}