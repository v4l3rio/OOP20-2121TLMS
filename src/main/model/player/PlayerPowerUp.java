package main.model.player;

/**
 * 
 * interface to change some player's values
 */
public interface PlayerPowerUp {
	
	/**
	 * 
	 * @param new player's color
	 * @param new player's speed
	 * @param new player's health
	 * @param new player's max number of jumps
	 */
	void transformation(PlayerColor color, int speed, int health, int jumps);
	
}
