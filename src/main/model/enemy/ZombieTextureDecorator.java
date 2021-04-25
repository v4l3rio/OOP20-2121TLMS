package main.model.enemy;

import java.util.Random;

import main.model.Texture;

/**
 * This Class models a Zombie with Texture
 */
public interface ZombieTextureDecorator extends Enemy{

	/**
	 * Enum with 2 types of sex {@link MALE} {@link FEMALE},
	 * and static method that return random gender
	 */
	enum Gender {
		
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
		public static Gender getRandom() {
			return Gender.values()[new Random().nextInt(Gender.values().length)];
		}
	}
	
	/**
	 * @return Texture map of this zombie
	 */
	Texture getTexture();
	
	
}
