package factories;

import model.TLMSType;
import model.TexturedGun;

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
