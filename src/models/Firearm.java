package models;

import com.almasb.fxgl.entity.Entity;

public abstract class Firearm extends Entity{
    
	private double shotDamage;
	private double speed;

	/**
	 * 
	 * @param speed set shot speed
	 */
    public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
     * Get shot speed.
     * @return the damage.
     */
	public double getSpeed() {
		return this.speed;
	};
	
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
    public void setHitDamage(final double shotDamage) {
        this.shotDamage = shotDamage;
    }
}
