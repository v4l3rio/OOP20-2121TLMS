package factories;

import java.util.HashMap;

import model.TLMSType;
import model.TexturedGun;
/**
 * 
 * Implements GunFactory, therefore implementing methods to create instances of game guns,
 * with preset values
 *
 */
public class TexturedGunFactoryImpl implements TexturedGunFactory{
	//Beretta92 preset specifications
	private final static int BRT_DMG = 3;
	private final static int BRT_MAXAMMO = 10;
	private final static double BRT_SHOT_SPEED = 600;
	private final static String BRT_GUN_IMG_PATH = "assets/textures/beretta92Gun.png";
	private final static String BRT_SHOT_IMG_PATH = "assets/textures/beretta92Shot.png";
	//Magmagun preset specifications
	private final static int MGM_DMG = 7;
	private final static int MGM_MAXAMMO = 7;
	private final static double MGM_SHOT_SPEED = 400;
	private final static String MGM_GUN_IMG_PATH = "assets/textures/magmaGun.png";
	private final static String MGM_SHOT_IMG_PATH = "assets/textures/magmaGunShot.png";
	public static final double MAGMA_GUN_DURATION = 7;
	//MachineGun preset specifications
	private final static int MCH_DMG = 5;
	//MachineGun ammo won't be decremented in the shoot() method: unlimited shots
	private final static int MCH_MAXAMMO = 1;
	private final static double MCH_SHOT_SPEED = 1000;
	private final static String MCH_GUN_IMG_PATH = "assets/textures/machineGun.png";
	private final static String MCH_SHOT_IMG_PATH = "assets/textures/machineGunShot.png";
	public static final double MACHINE_GUN_DURATION = 5;
	/**
	 * return a new instance of Gun, with Beretta92 specifications
	 * and standard shoot implementation
	 */
	public TexturedGun createBeretta92() {
		final var texturePaths = new HashMap<TLMSType, String>();
		texturePaths.put(TLMSType.GUN, BRT_GUN_IMG_PATH);
		texturePaths.put(TLMSType.SHOT, BRT_SHOT_IMG_PATH);
		return new TexturedGun(BRT_DMG, BRT_MAXAMMO, BRT_SHOT_SPEED, texturePaths) {
			@Override
			public void shoot() {
				super.setNAmmo(super.getNAmmo() - 1);
			}
		};
	}
	/**
	 * return a new instance of Gun, with MagmaGun specifications
	 * and standard shoot implementation
	 */
	public TexturedGun createMagmaGun() {
		final var texturePaths = new HashMap<TLMSType, String>();
		texturePaths.put(TLMSType.GUN, MGM_GUN_IMG_PATH);
		texturePaths.put(TLMSType.SHOT, MGM_SHOT_IMG_PATH);
		return new TexturedGun(MGM_DMG, MGM_MAXAMMO, MGM_SHOT_SPEED, texturePaths) {
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
	public TexturedGun createMachineGun() {
		final var texturePaths = new HashMap<TLMSType, String>();
		texturePaths.put(TLMSType.GUN, MCH_GUN_IMG_PATH);
		texturePaths.put(TLMSType.SHOT, MCH_SHOT_IMG_PATH);
		return new TexturedGun(MCH_DMG, MCH_MAXAMMO, MCH_SHOT_SPEED, texturePaths) {
			@Override
			public void shoot() {
				//do nothing, feature: unlimited ammo
			}
		};
	}
	

	@Override
	public TexturedGun gunFromType(final TLMSType type){
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
