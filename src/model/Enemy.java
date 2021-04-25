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
	public int getSpeed();
	
	/**
	 * @param life - life of enemy
	 */
	public void setLife(int life);
	
	/**
	 * 
	 * @return life of this enemy
	 */
	public int getLife();
	
	/**
	 * 
	 * @return UUID of this enemy
	 */
	public UUID getUUID();
	
	/**
	 * 
	 * @param dmg - set damage dealt by the enemy
	 */
	public void setDamage(int dmg);
	
	/**
	 * 
	 * @return damage dealt by the enemy
	 */
	public int getDamage();
}