package model;

import com.almasb.fxgl.entity.Entity;

import javafx.scene.image.Image;

public class MagmaGun extends Entity implements Firearm {
	
	private final Image shotTexture = new Image("assets/textures/myShotShrunk.png");
	
	private final static int MAXAMMO = 10;
	private final static int SHOTDMG = 5;
	private final static double SHOTSPEED = 800;
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

}
