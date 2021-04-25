package main.factories;

import main.model.TLMSType;
import main.model.gun.TexturedGun;

/**
 * Organizes the creation of textured guns.
 *
 */
public interface TexturedGunFactory {
	
	/**
	 * 
	 * @param type gun type
	 * @return an instance of a textured gun of type "type"
	 */
	TexturedGun getTexturedGun(TLMSType type);
}
