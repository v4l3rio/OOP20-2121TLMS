package factories;

import model.TLMSType;
import model.TexturedGun;

/**
 * Organizes the creation of textured guns
 *
 */
public interface TexturedGunFactory{

	/**
	 * 
	 * @return an instance of Beretta92
	 */
	TexturedGun createBeretta92();
	
	/**
	 * 
	 * @return an instance of MagmaGun
	 */
	TexturedGun createMagmaGun();

	/**
	 * 
	 * @return an instance of MachineGun
	 */
	TexturedGun createMachineGun();
	/**
	 * 
	 * @param type firearm type
	 * @return the matching firearm
	 */
	TexturedGun gunFromType(TLMSType type);
}