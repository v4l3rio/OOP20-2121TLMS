package factories;

import model.Firearm;

public class FirearmFactoryImpl implements FirearmFactory{

	private final static int BRT_DMG = 3;
	private final static int BRT_MAXAMMO = 8;
	private final static int BRT_SHOT_SPEED = 600;
	private final static String BRT_GUN_IMG_PATH = "assets/textures/beretta92Gun.png";
	private final static String BRT_SHOT_IMG_PATH = "assets/textures/beretta92Shot.png";

	private final static int MGM_DMG = 7;
	private final static int MGM_MAXAMMO = 5;
	private final static int MGM_SHOT_SPEED = 1000;
	private final static String MGM_GUN_IMG_PATH = "assets/textures/magmaGun.png";
	private final static String MGM_SHOT_IMG_PATH = "assets/textures/magmaGunShot.png";
	
	private final static int MCH_DMG = 5;
	private final static int MCH_MAXAMMO = 1;
	private final static int MCH_SHOT_SPEED = 1000;
	private final static String MCH_GUN_IMG_PATH = "assets/textures/machineGun.png";
	private final static String MCH_SHOT_IMG_PATH = "assets/textures/machineGunShot.png";
	
	public Firearm createBeretta92() {
		return new Firearm(BRT_DMG, BRT_MAXAMMO, BRT_SHOT_SPEED, BRT_GUN_IMG_PATH, BRT_SHOT_IMG_PATH) {
			@Override
			public void shoot() {
				super.setNAmmo(super.getNAmmo() - 1);
			}
		};
	}

	public Firearm createMagmaGun() {
		return new Firearm(MGM_DMG, MGM_MAXAMMO, MGM_SHOT_SPEED, MGM_GUN_IMG_PATH, MGM_SHOT_IMG_PATH) {
			@Override
			public void shoot() {
				super.setNAmmo(super.getNAmmo() - 1);
			}
		};
	}

	public Firearm createMachineGun() {
		return new Firearm(MCH_DMG, MCH_MAXAMMO, MCH_SHOT_SPEED, MCH_GUN_IMG_PATH, MCH_SHOT_IMG_PATH) {
			@Override
			public void shoot() {
				//do nothing, feature: unlimited ammo
			}
		};
	}
}
