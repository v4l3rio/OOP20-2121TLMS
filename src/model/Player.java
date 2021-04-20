package model;

/**
 * 
 * This interface gets and sets the values ​​of the main characteristics of the player
 */
public interface Player {

	/**
	 * 
	 * @return player's speed
	 */
	int getSpeed();

	/**
	 * 
	 * this method is used to set a new player's speed
	 * @param new speed
	 */
	void setSpeed(int speed);

	/**
	 * 
	 * @return player's health
	 */
	int getHealt();

	/**
	 * this method is used to set a new value for player's health
	 * @param health
	 */
	void setHealt(int healt);
	
	/**
	 * 
	 * @return health max
	 */
	int getMaxHeath();

	/**
	 * 
	 * @return the jump's number left
	 */
	int getNJumps();

	/**
	 * this method is used to set a new value of the max number of jumps
	 * @param maxJumps
	 */
	void setMaxJumps(int maxJumps);

	/**
	 * 
	 * this method is used to set the jump's number left to the value of the max number of jumps
	 */
	void resetNJumps();

	/**
	 * 
	 * this method is used to decrease the number of jumps left
	 */
	void decreaseJumps();

	/**
	 * 
	 * @return player's jump's height
	 */
	int getJumpHeight();
	
	/**
	 * 
	 * @return player's dimension
	 */
	double getDimension();
	
	/**
	 * 
	 * @return player's color
	 */
	PlayerColor getColor();
	
	/**
	 * 
	 * @param player's new color
	 */
	void setColor(PlayerColor color);

}