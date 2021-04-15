package model;

import javafx.scene.image.Image;

public class MachineGun implements Firearm {
	//this gun has unlimited bullets
	
	private final static int MAXAMMO = 1;
	private final static int SHOTDMG = 5;
	private final static double SHOTSPEED = 1000;
	
	private final static String FIREARMNAME = "MachineGun";
	
	//although it's the model, after some thinking, this seems the best place for the texture placing
	private final Image shotTexture = new Image("assets/textures/machineGunShot.png");
	private final Image gunTexture = new Image("assets/textures/machineGunTexture.png");
	
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
	}

    public int getShotDamage() {
        return SHOTDMG;
    }
	
	public void recharge() {
		nAmmo = MAXAMMO;
	}

	public Image getWeaponTexture() {
		return this.gunTexture;
	}

	public String getFirearmName() {
		return FIREARMNAME;
	}
	
}
