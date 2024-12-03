package com.example.demo;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = 0;
	private static final double Y_LOWER_BOUND = 670.0;
	private static final double X_UPPER_BOUND = 1125.0;
	private static final double X_LOWER_BOUND = 0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 50;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HORIZONTAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 60;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int m_HorizontalVelocityMultiplier;
	private int m_VerticalVelocityMultiplier;
	private int m_NumberOfKills;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		m_HorizontalVelocityMultiplier = 0;
		m_VerticalVelocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return m_NumberOfKills;
	}

	@Override
	public void updatePosition() { //split?
		if (isMovingVertically()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * m_VerticalVelocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
		if (isMovingHorizontally()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * m_HorizontalVelocityMultiplier);
			double newPosition = getLayoutX() + getTranslateX();
			if (newPosition < X_LOWER_BOUND || newPosition > X_UPPER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}
	
	@Override
	public void updateEntity() {
		updatePosition();
	}
	
	@Override
	public DestructibleEntity fireProjectile() {
		double projectileX = getProjectileXPosition(PROJECTILE_X_POSITION);
		double projectileY = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
		return new UserProjectile(projectileX, projectileY);
	}

	private boolean isMovingHorizontally() {
		return m_HorizontalVelocityMultiplier != 0;
	}

	private boolean isMovingVertically() {
		return m_VerticalVelocityMultiplier != 0;
	}

	public void moveUp() {
		m_VerticalVelocityMultiplier = -1;
	}

	public void moveDown() {
		m_VerticalVelocityMultiplier = 1;
	}

	public void moveRight() {
		m_HorizontalVelocityMultiplier = 1;
	}

	public void moveLeft() {
		m_HorizontalVelocityMultiplier = -1;
	}

	public void stopHorizontally() {
		m_HorizontalVelocityMultiplier = 0;
	}

	public void stopVertically() {
		m_VerticalVelocityMultiplier = 0;
	}

	public void incrementKillCount() {
		m_NumberOfKills++;
	}
}
