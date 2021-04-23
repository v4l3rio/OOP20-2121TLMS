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

	@Override
	public void reload() {
		nAmmo = maxAmmo;
	}

	@Override
    public int getDamage() {
        return shotDMG;
    }

	@Override
	public double getShotspeed() {
		return shotSpeed;
	}

	@Override
	public int getNAmmo() {
		return nAmmo;
	}

	@Override
	public void setNAmmo(final int nAmmo) {
		this.nAmmo = nAmmo;
	}

	@Override
	public int getMaxAmmo() {
		return this.maxAmmo;
	}

	@Override
	public boolean isSameTypeAs(final Gun otherGun) {
		return this.shotDMG == otherGun.getDamage() 
				&& this.shotSpeed == otherGun.getShotspeed()
				&& this.getMaxAmmo() == otherGun.getMaxAmmo();
	}
}
