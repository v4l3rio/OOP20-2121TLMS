package model;

import java.util.UUID;

/**
 * This class models a generic Enemy
 */
public interface Enemy {
	/**
	 * 
	 * @return speed of this enemy
	 */
	int getSpeed();
	
	/**
	 * 
	 * @param spd, speed of this enemy
	 */
	void setSpeed(int spd);
	
	/**
	 * @param life - life of enemy
	 */
	void setLife(int life);
	
	/**
	 * 
	 * @return life of this enemy
	 */
	int getLife();
	
	/**
	 * 
	 * @return UUID of this enemy
	 */
	UUID getUUID();
	
	/**
	 * 
	 * @param dmg - set damage dealt by the enemy
	 */
	void setDamage(int dmg);
	
	/**
	 * 
	 * @return damage dealt by the enemy
	 */
	int getDamage();
}