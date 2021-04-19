package model;

import com.almasb.fxgl.entity.Entity;

import javafx.scene.image.Image;

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
	
	private boolean isReloading = false;
	private int nAmmo;

	public abstract void shoot();
	
	public Gun(int shotDMG, int maxAmmo, double shotSpeed) {
		this.shotDMG = shotDMG;
		this.maxAmmo = maxAmmo;
		this.nAmmo = maxAmmo;
		this.shotSpeed = shotSpeed;
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
	public void setReloading(boolean isReloading) {
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
	public void setNAmmo(int nAmmo) {
		this.nAmmo = nAmmo;
	}
	/**
	 * 
	 * @param otherGun
	 * @return whether this gun is of the same type as otherGun, comparing main fields
	 */
	public boolean isSameTypeAs(Gun otherGun) {
		return this.shotDMG == otherGun.getShotDamage() 
				&& this.shotSpeed == otherGun.getShotspeed();
	}
}