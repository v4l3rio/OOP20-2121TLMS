package model;

public class MagmaGun extends Firearm {
	
	private final static String shotTexturePath = "assets/textures/magmaGunShot.png";
	private final static String gunTexturePath = "assets/textures/magmaGunTexture.png";
	private final static int MAXAMMO = 7;
	private final static int SHOTDMG = 10;
	private final static double SHOTSPEED = 800;
	
	public MagmaGun() {
		super(SHOTDMG, MAXAMMO, SHOTSPEED, shotTexturePath, gunTexturePath);
	}

	@Override
	public void shoot() {
		super.setNAmmo(super.getNAmmo() - 1);
	}

}
