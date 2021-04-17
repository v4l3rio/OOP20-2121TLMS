package factories;

import model.Firearm;

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
	
}