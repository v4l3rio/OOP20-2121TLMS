package model;

import com.almasb.fxgl.entity.Entity;

/**
 * Implements a gun model, leaving only the abstract method shoot() to be defined
 * in its implementations
 *
 */
public abstract class Gun extends Entity{

	//reload time, not to over-complicate, the same for each gun
	public final static double RELOAD_TIME = 1.5;
	
	private final int shotDMG;
	private final int maxAmmo;
	private final double shotSpeed;
	
	private boolean isReloading;
	private int nAmmo;

	public abstract void shoot();
	
	public Gun(final int shotDMG, final int maxAmmo, final double shotSpeed) {
		this.shotDMG = shotDMG;
		this.maxAmmo = maxAmmo;
		this.nAmmo = maxAmmo;
		this.shotSpeed = shotSpeed;
		this.isReloading = false;
	}
	/**
	 * refills ammo, setting them back to the max capacity
	 */
	public void reload() {
		nAmmo = maxAmmo;
	}
	/**
	 * 
	 * @return if the gun is being reloaded
	 */
	public boolean isReloading() {
		return isReloading;
	}
	/**
	 * 
	 * @param isReloading set the current reload state
	 */
	public void setReloading(final boolean isReloading) {
		this.isReloading = isReloading;
	}
	/**
	 * 
	 * @return shot damage
	 */
    public int getShotDamage() {
        return shotDMG;
    }
	/**
	 * 
	 * @return shot speed
	 */
	public double getShotspeed() {
		return shotSpeed;
	}
	/**
	 * 
	 * @return current ammunition number
	 */
	public int getNAmmo() {
		return nAmmo;
	}
	/**
	 * 
	 * @param nAmmo sets current ammunition number
	 */
	public void setNAmmo(final int nAmmo) {
		this.nAmmo = nAmmo;
	}
	/**
	 * 
	 * @param otherGun
	 * @return whether this gun is of the same type as otherGun, comparing main fields
	 */
	public boolean isSameTypeAs(final Gun otherGun) {
		return this.shotDMG == otherGun.getShotDamage() 
				&& this.shotSpeed == otherGun.getShotspeed();
	}
}