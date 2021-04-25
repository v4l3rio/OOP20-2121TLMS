package main.model.gun;

/**
 * Gun interface: sets the stage for gun general behavior.
 */
public interface Gun {
	
	/**
	 * defines guns behavior when asked to shoot.
	 */
	void shoot();
	
	/**
	 * refills ammo, setting them back to the max capacity.
	 */
	void reload();
	
	/**
	 * @return shot damage value.
	 */
    int getDamage();

	/**
	 * 
	 * @return shot speed value.
	 */
	double getShotspeed();
	
	/**
	 * 
	 * @return current ammunition number
	 */
	int getNAmmo();
	
	/**
	 * 
	 * @param nAmmo sets current ammunition number
	 */
	void setNAmmo(int nAmmo);
	
	/**
	 * 
	 * @return max ammunition capacity
	 */
	int getMaxAmmo();
	
	/**
	 * 
	 * @param otherGun
	 * @return whether this gun is of the same type as otherGun, comparing main fields
	 */
	boolean isSameTypeAs(Gun otherGun);
}
