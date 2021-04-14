package model;

import javafx.scene.image.Image;

public class MachineGun implements Firearm {
	//this gun has unlimited bullets
	
	//although it's the model, after some thinking, this seems the best place for the texture placing
	private final Image shotTexture = new Image("assets/textures/machineGunShot.png");
	private final Image gunTexture = new Image("assets/textures/machineGunTexture.png");
	//one third of real Beretta92 magazine
	private final static int MAXAMMO = 1;
	private final static int SHOTDMG = 5;
	private final static double SHOTSPEED = 1000;
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
	
	@Override
	public void recharge() {
		nAmmo = MAXAMMO;
	}

	@Override
	public Image getWeaponTexture() {
		return this.gunTexture;
	}

	@Override
	public String getName() {
		return "MachineGun";
	}
	
}
