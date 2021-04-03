package models;

import java.util.UUID;


public class Zombie implements Enemy{
	private UUID uuid;
	private int hp;
	private int speed;
	
	public Zombie(int hp, int speed) {
		this.uuid = UUID.randomUUID();
		this.hp = hp;
		this.speed = speed;
	}


	public void setHp(int hp) {
		this.hp = hp;
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
}
