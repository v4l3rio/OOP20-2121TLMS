package factories;

import model.Firearm;
import model.TLMSType;

/**
 * Organizes the creation of weapons
 *
 */
public interface FirearmFactory{

	/**
	 * 
	 * @return an instance of Beretta92
	 */
	public Firearm createBeretta92();
	
	/**
	 * 
	 * @return an instance of MagmaGun
	 */
	public Firearm createMagmaGun();

	/**
	 * 
	 * @return an instance of MachineGun
	 */
	public Firearm createMachineGun();
	/**
	 * 
	 * @param type firearm type
	 * @return the matching firearm
	 */
	public Firearm firearmFromType(TLMSType type) throws IllegalArgumentException;
}