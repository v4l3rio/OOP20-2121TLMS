package model;

import com.almasb.fxgl.entity.Entity;

import javafx.scene.image.Image;

public class Beretta92 extends Entity implements Firearm{
	//although it's the model, after some thinking, this seems the best place for the texture placing
	private final Image shotTexture = new Image("assets/textures/beretta92Shot.png");
	private final Image gunTexture = new Image("assets/textures/beretta92Gun.png");
	//one third of real Beretta92 magazine
	private final static int MAXAMMO = 5;
	private final static int SHOTDMG = 3;
	private final static double SHOTSPEED = 500;
	private int nAmmo = MAXAMMO;
	
	public Image getShotTexture() {
		return shotTexture;
	}

	public static double getShotspeed() {
		return SHOTSPEED;
	}
	
	public int getNAmmo() {
		return nAmmo;
	}

	public void decAmmo() {
		--this.nAmmo;
	}

    public int getShotDamage() {
        return SHOTDMG;
    }
	
	@Override
	public void recharge() {
		nAmmo = MAXAMMO;
	}

	@Override
	public Image getWeaponTexture() {
		return this.gunTexture;
	}
	
}
