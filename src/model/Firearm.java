package model;

import com.almasb.fxgl.entity.Entity;

import javafx.scene.image.Image;

public abstract class Firearm extends Entity{
	
	//although it's the model, after some thinking, this seems the best place for the texture placing
	// = new Image("assets/textures/beretta92Shot.png")
	// = new Image("assets/textures/beretta92Gun.png")
	private final Image shotTexture;
	private final Image gunTexture;
	private final int maxAmmo;
	private final int shotDMG;
	private final double shotSpeed;
	private int nAmmo;

	public abstract void shoot();
	
	public Firearm(int shotDMG, int maxAmmo, double shotSpeed, String shotTexturePath, String gunTexturePath) {
		this.shotDMG = shotDMG;
		this.maxAmmo = maxAmmo;
		this.nAmmo = maxAmmo;
		this.shotSpeed = shotSpeed;
		this.shotTexture = new Image(shotTexturePath);
		this.gunTexture = new Image(gunTexturePath);
	}
	
	public Image getShotTexture() {
		return shotTexture;
	}

	public double getShotspeed() {
		return shotSpeed;
	}
	
	public int getNAmmo() {
		return nAmmo;
	}
	
	public void setNAmmo(int nAmmo) {
		this.nAmmo = nAmmo;
	}

    public int getShotDamage() {
        return shotDMG;
    }
	
	public void reload() {
		nAmmo = maxAmmo;
	}

	public Image getWeaponTexture() {
		return this.gunTexture;
	}
}