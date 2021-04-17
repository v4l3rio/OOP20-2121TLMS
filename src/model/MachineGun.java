package model;

public class MachineGun extends Firearm {
	//although it's the model, after some thinking, this seems the best place for the texture placing
	private final static String shotTexturePath = "assets/textures/machineGunShot.png";
	private final static String gunTexturePath = "assets/textures/machineGunTexture.png";
	private final static int MAXAMMO = 1;
	private final static int SHOTDMG = 5;
	private final static double SHOTSPEED = 1000;

	//this gun has unlimited bullets
	public MachineGun() {
		super(SHOTDMG, MAXAMMO, SHOTSPEED, shotTexturePath, gunTexturePath);
	}

	@Override
	public void shoot() {
		//do nothing here, feature: unlimited ammo
	}
}
