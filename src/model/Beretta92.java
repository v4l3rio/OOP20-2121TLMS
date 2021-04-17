package model;

public class Beretta92 extends Firearm{
	//although it's the model, after some thinking, this seems the best place for the texture placing
	private final static String shotTexturePath = "assets/textures/beretta92Shot.png";
	private final static String gunTexturePath = "assets/textures/beretta92Gun.png";
	private final static int MAXAMMO = 5;
	private final static int SHOTDMG = 3;
	private final static double SHOTSPEED = 500;
	
	public Beretta92() {
		super(SHOTDMG, MAXAMMO, SHOTSPEED, shotTexturePath, gunTexturePath);
	}

	@Override
	public void shoot() {
		super.setNAmmo(super.getNAmmo() - 1);
	}
}
