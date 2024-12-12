package com.example.demo.entities;

import com.example.demo.gui.ShieldImage;

import java.util.*;

/**
 * Represents a Boss entity in the game, which is a type of FighterPlane.
 * The Boss has a shield, a specific move pattern, and can fire projectiles.
 */
public class Boss extends FighterPlane {

	/**
	 * The image name for the boss plane.
	 */
	private static final String IMAGE_NAME = "bossPlane.png";

	/**
	 * The initial X position of the boss.
	 */
	private static final double INITIAL_X_POSITION = 1100.0;

	/**
	 * The initial Y position of the boss.
	 */
	private static final double INITIAL_Y_POSITION = 400;

	/**
	 * The Y position offset for the boss's projectiles.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 15.0;

	/**
	 * The probability of the boss having a shield.
	 */
	private static final double BOSS_SHIELD_PROBABILITY = 0.02;

	/**
	 * The height of the boss image.
	 */
	private static final int IMAGE_HEIGHT = 85;

	/**
	 * The vertical velocity of the boss.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * The health of the boss.
	 */
	private static final int HEALTH = 5;

	/**
	 * The frequency of moves per cycle for the boss.
	 */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/**
	 * A constant representing zero.
	 */
	private static final int ZERO = 0;

	/**
	 * The maximum number of frames the boss can move in the same direction.
	 */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/**
	 * The upper bound for the Y position of the boss.
	 */
	private static final int Y_POSITION_UPPER_BOUND = 0;

	/**
	 * The lower bound for the Y position of the boss.
	 */
	private static final int Y_POSITION_LOWER_BOUND = 600;

	/**
	 * The maximum number of frames the boss can have its shield activated.
	 */
	private static final int MAX_FRAMES_WITH_SHIELD = 60;

	/**
	 * The list of next moves for the boss.
	 */
	private final List<Integer> m_NextMoves;

	/**
	 * Indicates whether the boss is shielded.
	 */
	private boolean m_IsShielded;

	/**
	 * The number of consecutive moves in the same direction.
	 */
	private int m_ConsecutiveMovesInSameDirection;

	/**
	 * The index of the current move in the move pattern.
	 */
	private int m_IndexOfCurrentMove;

	/**
	 * The number of frames the shield has been activated.
	 */
	private int m_FramesWithShieldActivated;

	/**
	 * The shield image for the boss.
	 */
	private final ShieldImage m_Shield;

	/**
	 * The fire rate of the boss.
	 */
	private final double m_BossFireRate;

	/**
	 * Constructs a Boss with the specified fire rate.
	 *
	 * @param fireRate the rate at which the Boss fires projectiles
	 */
	public Boss(double fireRate) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		m_Shield = new ShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION);
		m_NextMoves = new ArrayList<>();
		m_ConsecutiveMovesInSameDirection = 3;
		m_IndexOfCurrentMove = 0;
		m_FramesWithShieldActivated = 0;
		m_IsShielded = false;
		m_BossFireRate = fireRate;
		initializeMovePattern();
	}

	/**
	 * Returns the shield image associated with the Boss.
	 *
	 * @return the shield image
	 */
	public ShieldImage getShieldImage() {
		return m_Shield;
	}

	/**
	 * Updates the position of the Boss based on its move pattern.
	 */
	@Override
	void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		m_Shield.setLayoutY(currentPosition);
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the Boss entity, including its position and shield status.
	 */
	@Override
	public void updateEntity() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile from the Boss.
	 *
	 * @return a new BossProjectile if the Boss is firing in the current frame, otherwise null
	 */
	@Override
	public DestructibleEntity fireProjectile() {
		return isBossFiringInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Takes damage, reducing the Boss's health if the shield is not active.
	 */
	@Override
	public void takeDamage() {
		if (!m_IsShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Returns the next move in the Boss's move pattern.
	 *
	 * @return the next move
	 */
	private int getNextMove() {
		int currentMove = m_NextMoves.get(m_IndexOfCurrentMove);
		m_ConsecutiveMovesInSameDirection++;
		if (m_ConsecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(m_NextMoves);
			m_ConsecutiveMovesInSameDirection = 3;
			m_IndexOfCurrentMove++;
		}
		if (m_IndexOfCurrentMove == m_NextMoves.size()) {
			m_IndexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Returns the initial position for the Boss's projectile.
	 *
	 * @return the initial position for the projectile
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Initializes the move pattern for the Boss.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			m_NextMoves.add(VERTICAL_VELOCITY);
			m_NextMoves.add(-VERTICAL_VELOCITY);
			m_NextMoves.add(ZERO);
		}
		Collections.shuffle(m_NextMoves);
	}

	/**
	 * Updates the shield status of the Boss.
	 */
	private void updateShield() {
		if (m_IsShielded) {
			m_FramesWithShieldActivated++;
			m_Shield.showShield();
		} else if (shouldShieldActivate()) {
			activateShield();
		}
		if (isShieldExhausted()) {
			deactivateShield();
		}
	}

	/**
	 * Determines if the Boss is firing in the current frame.
	 *
	 * @return true if the Boss is firing, otherwise false
	 */
	private boolean isBossFiringInCurrentFrame() {
		return Math.random() < m_BossFireRate;
	}

	/**
	 * Determines if the shield should activate.
	 *
	 * @return true if the shield should activate, otherwise false
	 */
	private boolean shouldShieldActivate() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Determines if the shield is exhausted.
	 *
	 * @return true if the shield is exhausted, otherwise false
	 */
	private boolean isShieldExhausted() {
		return m_FramesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the shield for the Boss.
	 */
    void activateShield() {
		m_IsShielded = true;
		m_Shield.showShield();
	}

	/**
	 * Deactivates the shield for the Boss.
	 */
	private void deactivateShield() {
		m_IsShielded = false;
		m_Shield.hideShield();
		m_FramesWithShieldActivated = 0;
	}
}