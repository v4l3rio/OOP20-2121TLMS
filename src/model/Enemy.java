package model;

import java.util.UUID;

/**
 * 
 * @author Valerio Di Zio
 * @version 1.0
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
	 * @return life of this enemy
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
	 * @param dmg, set damage dealt by the enemy
	 */
	void setDamage(int dmg);
	
	/**
	 * 
	 * @return damage dealt by the enemy
	 */
	int getDamage();
}