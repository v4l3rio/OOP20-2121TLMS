package factories;

import model.Firearm;
import model.TLMSType;
/**
 * 
 * Implements FirearmFactory, therefore implementing methods to create instances of game firearms,
 * with preset values
 *
 */
public class FirearmFactoryImpl implements FirearmFactory{
	//Beretta92 preset specifications
	private final static int BRT_DMG = 3;
	private final static int BRT_MAXAMMO = 10;
	private final static int BRT_SHOT_SPEED = 600;
	private final static String BRT_GUN_IMG_PATH = "assets/textures/beretta92Gun.png";
	private final static String BRT_SHOT_IMG_PATH = "assets/textures/beretta92Shot.png";
	//Magmagun preset specifications
	private final static int MGM_DMG = 7;
	private final static int MGM_MAXAMMO = 7;
	private final static int MGM_SHOT_SPEED = 1000;
	private final static String MGM_GUN_IMG_PATH = "assets/textures/magmaGun.png";
	private final static String MGM_SHOT_IMG_PATH = "assets/textures/magmaGunShot.png";
	public static final double MAGMA_GUN_DURATION = 7;
	//MachineGun preset specifications
	private final static int MCH_DMG = 5;
	private final static int MCH_MAXAMMO = 1;
	private final static int MCH_SHOT_SPEED = 1000;
	private final static String MCH_GUN_IMG_PATH = "assets/textures/machineGun.png";
	private final static String MCH_SHOT_IMG_PATH = "assets/textures/machineGunShot.png";
	public static final double MACHINE_GUN_DURATION = 5;
	/**
	 * return a new instance of Firearm, with Beretta92 specifications
	 * and standard shoot implementation
	 */
	public Firearm createBeretta92() {
		return new Firearm(BRT_DMG, BRT_MAXAMMO, BRT_SHOT_SPEED, BRT_GUN_IMG_PATH, BRT_SHOT_IMG_PATH) {
			@Override
			public void shoot() {
				super.setNAmmo(super.getNAmmo() - 1);
			}
		};
	}
	/**
	 * return a new instance of Firearm, with MagmaGun specifications
	 * and standard shoot implementation
	 */
	public Firearm createMagmaGun() {
		return new Firearm(MGM_DMG, MGM_MAXAMMO, MGM_SHOT_SPEED, MGM_GUN_IMG_PATH, MGM_SHOT_IMG_PATH) {
			@Override
			public void shoot() {
				super.setNAmmo(super.getNAmmo() - 1);
			}
			
		};
	}
	/**
	 * return a new instance of Firearm, with MachineGun specifications.
	 * method shoot doesn't decrement ammo, therefore setting them unlimited
	 */
	public Firearm createMachineGun() {
		return new Firearm(MCH_DMG, MCH_MAXAMMO, MCH_SHOT_SPEED, MCH_GUN_IMG_PATH, MCH_SHOT_IMG_PATH) {
			@Override
			public void shoot() {
				//do nothing, feature: unlimited ammo
			}
		};
	}
	
	public Firearm firearmFromType(TLMSType type) {
		if(type.equals(TLMSType.MACHINEGUN)) {
			return createMachineGun();
		} else if(type.equals(TLMSType.MAGMAGUN)) {
			return createMagmaGun();
		} else if(type.equals(TLMSType.BERETTA92)){
			return createBeretta92();
		} else {
			throw new IllegalArgumentException();
		}
	};
}
