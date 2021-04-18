package model;

import com.almasb.fxgl.entity.Entity;

import javafx.scene.image.Image;

public abstract class Firearm extends Entity{
	
	//although it's the model, after some thinking, this seems the best place for the texture placing
	private final int shotDMG;
	private final int maxAmmo;
	private final double shotSpeed;
	private final Image gunIMG;
	private final Image shotIMG;
	
	private int nAmmo;

	public abstract void shoot();
	
	public Firearm(int shotDMG, int maxAmmo, double shotSpeed, String gunIMGPath, String shotIMGPath) {
		this.shotDMG = shotDMG;
		this.maxAmmo = maxAmmo;
		this.nAmmo = maxAmmo;
		this.shotSpeed = shotSpeed;
		this.gunIMG = new Image(gunIMGPath);
		this.shotIMG = new Image(shotIMGPath);
	}

    public int getShotDamage() {
        return shotDMG;
    }
	
	public double getShotspeed() {
		return shotSpeed;
	}

	public Image getGunIMG() {
		return this.gunIMG;
	}
	
	public Image getShotIMG() {
		return shotIMG;
	}
	
	public int getNAmmo() {
		return nAmmo;
	}
	
	public void setNAmmo(int nAmmo) {
		this.nAmmo = nAmmo;
	}
	
	public void reload() {
		nAmmo = maxAmmo;
	}

	public boolean isSameTypeAs(Firearm firearm) {
		return this.shotDMG == firearm.getShotDamage() 
				&& this.shotSpeed == firearm.getShotspeed() 
				&& this.getShotIMG() == firearm.getGunIMG()
				&& this.getGunIMG() == firearm.getGunIMG();
	}
}