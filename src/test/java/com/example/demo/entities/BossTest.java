package com.example.demo.entities;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class BossTest extends ApplicationTest {

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