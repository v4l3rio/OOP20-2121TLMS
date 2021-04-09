package model;

import java.util.UUID;

import javafx.scene.image.Image;

public class Shot extends Firearm{
	
	private final UUID uuid;
	private double speed;

	private int shotDamage;
	
	public Shot(int shotDamage, double speed) {
		this.uuid = UUID.randomUUID();
		this.speed = speed;
		this.shotDamage = shotDamage;
	}
	
	/**
     * Get damage per shot.
     * @return the damage.
     */
    public int getShotDamage() {
        return shotDamage;
    }
    
    /**
    *
     * @param shotDamage set damage per shot
    */
   public void setShotDamage(final int shotDamage) {
       this.shotDamage = shotDamage;
   }

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	public double getShotSpeed() {
		return speed;
	}
	
	public UUID getUUID() {
		return uuid;
	}
}
