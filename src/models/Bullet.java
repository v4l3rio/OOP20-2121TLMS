package models;

import java.util.UUID;

import javafx.scene.image.Image;

public class Bullet extends Firearm{
	
	private UUID uuid;
	private Image movementTexture;
	private int speed;
	
	public Bullet(int speed, Image movementTexture) {
		this.uuid = UUID.randomUUID();
		this.movementTexture = movementTexture;
		this.speed = speed;
	}

	public Image getMovementTexture() {
		return movementTexture;
	}

	public void setMovementTexture(Image movementTexture) {
		this.movementTexture = movementTexture;
	}

	@Override
	public double getSpeed() {
		return speed;
	}
	
	public UUID getUUID() {
		return uuid;
	}
}
