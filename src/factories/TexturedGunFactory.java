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
	public TexturedGun createBeretta92();
	
	/**
	 * 
	 * @return an instance of MagmaGun
	 */
	public TexturedGun createMagmaGun();

	/**
	 * 
	 * @return an instance of MachineGun
	 */
	public TexturedGun createMachineGun();
	/**
	 * 
	 * @param type firearm type
	 * @return the matching firearm
	 */
	public TexturedGun gunFromType(TLMSType type) throws IllegalArgumentException;
}