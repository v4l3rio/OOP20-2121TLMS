package model;

import com.almasb.fxgl.entity.Entity;

public abstract class Firearm extends Entity{
    
	//basic Firearm power, applied to bullet, resulting in speed
	private double shotSpeed;

	/**
	 * 
	 * @param set shot speed
	 */
    public void setShotSpeed(double speed) {
		this.shotSpeed = speed;
	}

	/**
     * Get shot speed.
     * @return the damage.
     */
	public double getShotSpeed() {
		return this.shotSpeed;
	};
}
