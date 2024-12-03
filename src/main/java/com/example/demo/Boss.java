package com.example.demo;

import java.util.*;

public class Boss extends FighterPlane {

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1100.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 15.0;
	private static final double BOSS_FIRE_RATE = .01;
	private static final double BOSS_SHIELD_PROBABILITY = 0.02;
	private static final int IMAGE_HEIGHT = 85;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 2;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = 0;
	private static final int Y_POSITION_LOWER_BOUND = 600;
	private static final int MAX_FRAMES_WITH_SHIELD = 60;
	private final List<Integer> m_NextMoves;
	private boolean m_IsShielded;
	private int m_ConsecutiveMovesInSameDirection;
	private int m_IndexOfCurrentMove;
	private int m_FramesWithShieldActivated;
	private ShieldImage m_Shield;

	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		m_Shield = new ShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION);
		m_NextMoves = new ArrayList<>();
		m_ConsecutiveMovesInSameDirection = 0;
		m_IndexOfCurrentMove = 0;
		m_FramesWithShieldActivated = 0;
		m_IsShielded = false;
		initializeMovePattern();
	}

	public ShieldImage getShieldImage() {
		return m_Shield;
	}

	private int getNextMove() {
		int currentMove = m_NextMoves.get(m_IndexOfCurrentMove);
		m_ConsecutiveMovesInSameDirection++;
		if (m_ConsecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(m_NextMoves);
			m_ConsecutiveMovesInSameDirection = 5;
			m_IndexOfCurrentMove++;
		}
		if (m_IndexOfCurrentMove == m_NextMoves.size()) {
			m_IndexOfCurrentMove = 0;
		}
		return currentMove;
	}

	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		m_Shield.setLayoutY(currentPosition);
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}
	
	@Override
	public void updateEntity() {
		updatePosition();
		updateShield();
	}

	@Override
	public DestructibleEntity fireProjectile() {
		return isBossFiringInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}
	
	@Override
	public void takeDamage() {
		if (!m_IsShielded) {
			super.takeDamage();
		}
	}

	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			m_NextMoves.add(VERTICAL_VELOCITY);
			m_NextMoves.add(-VERTICAL_VELOCITY);
			m_NextMoves.add(ZERO);
		}
		Collections.shuffle(m_NextMoves);
	}

	private void updateShield() {
		if (m_IsShielded) {
			m_FramesWithShieldActivated++;
			m_Shield.showShield();
		}
		else if (shouldShieldActivate()) {
			activateShield();
		}
		if (isShieldExhausted()) {
			deactivateShield();
		}
	}

	private boolean isBossFiringInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	private boolean shouldShieldActivate() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	private boolean isShieldExhausted() {
		return m_FramesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	private void activateShield() {
		m_IsShielded = true;
		m_Shield.showShield();
	}

	private void deactivateShield() {
		m_IsShielded = false;
		m_Shield.hideShield();
		m_FramesWithShieldActivated = 0;
	}
}
