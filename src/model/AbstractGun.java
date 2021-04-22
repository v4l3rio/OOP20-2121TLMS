package model;

/**
 * Implements a gun model, leaving only the abstract method shoot() to be defined
 * in its implementations
 *
 */
public abstract class AbstractGun implements Gun{
	
	private final int shotDMG;
	private final int maxAmmo;
	private final double shotSpeed;
	private int nAmmo;

	public abstract void shoot();
	
	public AbstractGun(final int shotDMG, final int maxAmmo, final double shotSpeed) {
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
	 * @return shot damage
	 */
    public int getDamage() {
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
	 * @return max ammunition capacity
	 */
	public int getMaxAmmo() {
		return this.maxAmmo;
	}
	/**
	 * 
	 * @param otherGun
	 * @return whether this gun is of the same type as otherGun, comparing main fields
	 */
	public boolean isSameTypeAs(final Gun otherGun) {
		return this.shotDMG == otherGun.getDamage() 
				&& this.shotSpeed == otherGun.getShotspeed()
				&& this.getMaxAmmo() == otherGun.getMaxAmmo();
	}
}
