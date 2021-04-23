package factories;

import java.util.HashMap;

import model.TLMSType;
import model.TexturedGun;
/**
 * Implements GunFactory, therefore implementing methods to create instances of game guns,
 * with preset values.
 */
public final class TexturedGunFactoryImpl implements TexturedGunFactory {
	
	//Beretta92 preset specifications
	private static final int BRT_DMG = 3;
	private static final int BRT_MAXAMMO = 10;
	private static final double BRT_SHOT_SPEED = 750;
	private static final String BRT_GUN_IMG_PATH = "assets/textures/beretta92Gun.png";
	private static final String BRT_SHOT_IMG_PATH = "assets/textures/beretta92Shot.png";
	//Magmagun preset specifications
	private static final int MGM_DMG = 100;
	private static final int MGM_MAXAMMO = 7;
	private static final double MGM_SHOT_SPEED = 650;
	private static final String MGM_GUN_IMG_PATH = "assets/textures/magmaGun.png";
	private static final String MGM_SHOT_IMG_PATH = "assets/textures/magmaGunShot.png";
	/**
	 * MagmGun duration time as a "power up".
	 */
	public static final double MAGMA_GUN_DURATION = 7;
	//MachineGun preset specifications
	private static final int MCH_DMG = 5;
	//MachineGun ammo won't be decremented in the shoot() method: unlimited shots
	private static final int MCH_MAXAMMO = 1;
	private static final double MCH_SHOT_SPEED = 900;
	private static final String MCH_GUN_IMG_PATH = "assets/textures/machineGun.png";
	private static final String MCH_SHOT_IMG_PATH = "assets/textures/machineGunShot.png";
	/**
	 * MachineGun duration time as a "power up".
	 */
	public static final double MACHINE_GUN_DURATION = 5;
	
	/**
	 * @return a new instance of Gun, with Beretta92 specifications
	 * and standard shoot implementation.
	 */
	private TexturedGun createBeretta92() {
		final var texturePaths = new HashMap<TLMSType, String>();
		texturePaths.put(TLMSType.GUN, BRT_GUN_IMG_PATH);
		texturePaths.put(TLMSType.SHOT, BRT_SHOT_IMG_PATH);
		return new TexturedGun(BRT_DMG, BRT_MAXAMMO, BRT_SHOT_SPEED, texturePaths) {
			@Override
			public void shoot() {
				if (this.getNAmmo() <= 0) {
					throw new IllegalStateException();
				} else {
					super.setNAmmo(super.getNAmmo() - 1);
				}
			}
		};
	}
	
	/**
	 * @return a new instance of Gun, with MagmaGun specifications
	 * and standard shoot implementation.
	 */
	private TexturedGun createMagmaGun() {
		final var texturePaths = new HashMap<TLMSType, String>();
		texturePaths.put(TLMSType.GUN, MGM_GUN_IMG_PATH);
		texturePaths.put(TLMSType.SHOT, MGM_SHOT_IMG_PATH);
		return new TexturedGun(MGM_DMG, MGM_MAXAMMO, MGM_SHOT_SPEED, texturePaths) {
			@Override
			public void shoot() {
				if (this.getNAmmo() <= 0) {
					throw new IllegalStateException();
				} else {
					super.setNAmmo(super.getNAmmo() - 1);
				}
			}
		};
	}
	/**
	 * @return	a new instance of Firearm, with MachineGun specifications,
	 * method shoot doesn't decrement ammo, therefore setting them unlimited.
	 */
	private TexturedGun createMachineGun() {
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
	public TexturedGun getTexturedGun(final TLMSType type) {
		if (type.equals(TLMSType.MACHINEGUN)) {
			return createMachineGun();
		} else if (type.equals(TLMSType.MAGMAGUN)) {
			return createMagmaGun();
		} else if (type.equals(TLMSType.BERETTA92)) {
			return createBeretta92();
		} else {
			throw new IllegalArgumentException();
		}
	};
}
