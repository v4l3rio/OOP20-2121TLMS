package model;

import java.util.Random;

/**
 * This Class models a Zombie with Texture
 */
public interface ZombieTextureDecorator extends Enemy{

	/**
	 * Enum with 2 types of sex {@link MALE} {@link FEMALE},
	 * and static method that return random gender
	 */
	public static enum GENDER {
		
		/**
		 * Male gender
		 */
		MALE,
		
		/**
		 * Female gender
		 */
		FEMALE;
		
		/**
		 * Static method that return random gender
		 */
		public static GENDER getRandom() {
			return GENDER.values()[new Random().nextInt(GENDER.values().length)];
		}
	}
	
	/**
	 * @return Texture map of this zombie
	 */
	public Texture getTexture();
	
}
