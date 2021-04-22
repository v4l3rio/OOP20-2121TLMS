package model;

import java.util.UUID;

/**
 * Model Generic Zombie
 */
public class Zombie implements Enemy{
	final private UUID uuid;
	private int life;
	private int speed;
	private int damage;
	
	/**
	 * @param hp, life of this zombie
	 * @param speed, speed of this zombie
	 * @param damage, damage that the zombie does in contact with the player
	 */
	public Zombie(final int life, final int speed, final int damage) {
		this.uuid = UUID.randomUUID();
		this.life = life;
		this.speed = speed;
		this.damage = damage;
	}


	public void setLife(final int life) {
		this.life = life;
	}
	
	public void setDamage(final int dmg) {
		this.damage = dmg;
	}


	@Override
	public void setSpeed(final int spd) {
		this.speed = spd;
		
	}


	public UUID getUUID() {
		return uuid;
	}

	@Override
	public int getSpeed() {
		
		return speed;
	}

	@Override
	public int getLife() {
		return life;
	}


	@Override
	public int getDamage() {
		return this.damage;
	}

	
}