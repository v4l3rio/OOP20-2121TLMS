package model;

import com.almasb.fxgl.entity.Entity;

public abstract class Firearm extends Entity{

	//shotDamage to be set in implementation
	protected int shotDamage = 0;
	private int nAmmo;

	public int getNAmmo() {
		return nAmmo;
	}

	public void setNAmmo(int nAmmo) {
		this.nAmmo = nAmmo;
	}
	
	/**
     * Get damage per shot.
     * @return the damage.
     */
    public int getShotDamage() {
        return shotDamage;
    }
}
