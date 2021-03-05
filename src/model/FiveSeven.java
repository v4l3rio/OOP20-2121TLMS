package model;

public class FiveSeven implements Weapon {
	final static int CAPACITY = 10;
	final int reloadTime = 2;
	int nAmmo = CAPACITY;
	
	@Override
	public void shoot(Direction dir) throws IllegalAccessException {
		if(nAmmo < 0) {
			throw new IllegalAccessException("no more ammos, reload");
		}
		nAmmo--;
		Bullet bullet = new Bullet(dir);
	}

	@Override
	public void setAmmo(int n) {
		this.nAmmo = n;
	}

	@Override
	public int getReloadTime() {
		return this.reloadTime;
	}

	@Override
	public int getAmmo() {
		return this.nAmmo;
	}

}
