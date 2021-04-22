package model;

import java.util.Random;


/**
 * Interface that defines the movement of an entity
 */
public interface Moveable {
	
	/**
	 * Directions that the entity can take
	 * {@link #LEFT}
	 * {@link #RIGHT}
	 * {@link #STOP}
	 * {@link #JUMP}
	 */
	enum Directions {
		
		/**
		 * Movement to the left
		 */
		LEFT,
		
		/**
		 * Movement to the right
		 */
		RIGHT, 
		
		/**
		 * Stop movement
		 */
		STOP, 
		
		/**
		 * Upward movement
		 */
		JUMP;
		
		public static Directions getRandom() {
			return Directions.values()[new Random().nextInt(Directions.values().length)];
		}
	}
	
	/**
	 * Define two type of movement
	 * {@link #FOLLOW}
	 * {@link #RANDOM}
	 */
	enum TypeOfMovement{
		
		/**
		 * The entity will move following the player
		 */
		FOLLOW, 
		
		/**
		 * The entity will move randomly
		 */
		RANDOM;
		
		public static TypeOfMovement getRandom() {
			return TypeOfMovement.values()[new Random().nextInt(TypeOfMovement.values().length)];
		}
		
	}
	/**
	 * Moves the entity to the left
	 */
	void left();
	
	/**
	 * Moves the entity to the right
	 */
	void right();
	
	/**
	 * Stop the entity movement
	 */
	void stop();
	
	/**
	 * Moves the entity up
	 */
	void jump();
}