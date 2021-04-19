package factories;

import model.Gun;
import model.TLMSType;

/**
 * Organizes the creation of weapons
 *
 */
public interface GunFactory{

	/**
	 * 
	 * @return an instance of Beretta92
	 */
	public Gun createBeretta92();
	
	/**
	 * 
	 * @return an instance of MagmaGun
	 */
	public Gun createMagmaGun();

	/**
	 * 
	 * @return an instance of MachineGun
	 */
	public Gun createMachineGun();
	/**
	 * 
	 * @param type firearm type
	 * @return the matching firearm
	 */
	public Gun gunFromType(TLMSType type) throws IllegalArgumentException;
}