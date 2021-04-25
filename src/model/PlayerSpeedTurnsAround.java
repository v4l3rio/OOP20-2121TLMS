package model;

/**
 * 
 * this class implements {@link PlayerSpeedStrategy}
 */
public class PlayerSpeedTurnsAround implements PlayerSpeedStrategy {
	
	private final int SPEED_LESS = 0;

	@Override
	public int getVelocity() {
		return this.SPEED_LESS;
	}

}
