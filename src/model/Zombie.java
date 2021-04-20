package model;

import java.util.UUID;

/**
 * Model Generic Zombie
 */
public class Zombie implements Enemy{
	private UUID uuid;
	private int life;
	private int speed;
	private int damage;
	
	/**
	 * @param hp, life of this zombie
	 * @param speed, speed of this zombie
	 * @param damage, damage that the zombie does in contact with the player
	 */
	public Zombie(int life, int speed, int damage) {
		this.uuid = UUID.randomUUID();
		this.life = life;
		this.speed = speed;
		this.damage = damage;
	}


	public void setLife(int life) {
		this.life = life;
	}
	
	public void setDamage(int dmg) {
		this.damage = dmg;
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