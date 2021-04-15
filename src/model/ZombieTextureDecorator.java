package model;

import java.util.Random;

/**
 * 
 * @version 1.1
 * This Class models a Zombie with Texture
 */
public interface ZombieTextureDecorator extends Enemy{
	
	public static enum GENDER {
		MALE, FEMALE;
		
		public static GENDER getRandom() {
			return GENDER.values()[new Random().nextInt(GENDER.values().length)];
		}
	}
	
	/**
     * @return Texture of zombie
     */
	public Texture getTexture();
	
}
