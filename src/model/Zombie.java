package model;


import java.util.UUID;

import javafx.scene.image.Image;

public class Zombie implements Enemy{
	private UUID uuid;
	private int hp;
	private Image idleTexture;
	private Image movementTexture;
	private int speed;
	
	public Zombie(int hp, int speed, Image idleTexture, Image movementTexture) {
		this.uuid = UUID.randomUUID();
		this.hp = hp;
		this.idleTexture = idleTexture;
		this.movementTexture = movementTexture;
		this.speed = speed;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}

	public Image getIdleTexture() {
		return idleTexture;
	}

	public void setIdleTexture(Image idleTexture) {
		this.idleTexture = idleTexture;
	}

	public Image getMovementTexture() {
		return movementTexture;
	}

	public void setMovementTexture(Image movementTexture) {
		this.movementTexture = movementTexture;
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
