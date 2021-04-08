package model;

import java.util.UUID;

import javafx.scene.image.Image;

public class Bullet extends Firearm{
	
	private final UUID uuid;
	private Image movementTexture;
	private double speed;
	private int shotDamage;
	
	public Bullet(int shotDamage, double speed, Image movementTexture) {
		this.uuid = UUID.randomUUID();
		this.movementTexture = movementTexture;
		this.speed = speed;
		this.shotDamage = shotDamage;
	}
	
	/**
     * Get damage per shot.
     * @return the damage.
     */
    public double getShotDamage() {
        return shotDamage;
    }
    
    /**
    *
     * @param hitDamage set damage per shot
    */
   public void setHitDamage(final int shotDamage) {
       this.shotDamage = shotDamage;
   }

   /**
    * 
    * @return Bullet texture
    */
	public Image getMovementTexture() {
		return movementTexture;
	}

	/**
	 * sets the texture
	 * @param movementTexture
	 */
	public void setMovementTexture(Image movementTexture) {
		this.movementTexture = movementTexture;
	}

	@Override
	public double getShotSpeed() {
		return speed;
	}
	
	public UUID getUUID() {
		return uuid;
	}
}
