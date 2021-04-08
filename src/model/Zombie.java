package model;

import java.util.UUID;


public class Zombie implements Enemy{
	private UUID uuid;
	private int hp;
	private int speed;
	private int damage;
	
	public Zombie(int hp, int speed, int damage) {
		this.uuid = UUID.randomUUID();
		this.hp = hp;
		this.speed = speed;
		this.damage = damage;
	}


	public void setHp(int hp) {
		this.hp = hp;
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
		
		return hp;
	}


	@Override
	public int getDamage() {
		return this.damage;
	}


	
	
}