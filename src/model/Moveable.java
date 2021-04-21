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
	public static enum DIRECTIONS {
		
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
		
		public static DIRECTIONS getRandom() {
			return DIRECTIONS.values()[new Random().nextInt(DIRECTIONS.values().length)];
		}
	}
	
	/**
	 * Define two type of movement
	 * {@link #FOLLOW}
	 * {@link #RANDOM}
	 */
	public static enum TYPE_OF_MOVEMENT{
		
		/**
		 * The entity will move following the player
		 */
		FOLLOW, 
		
		/**
		 * The entity will move randomly
		 */
		RANDOM;
		
		public static TYPE_OF_MOVEMENT getRandom() {
			return TYPE_OF_MOVEMENT.values()[new Random().nextInt(TYPE_OF_MOVEMENT.values().length)];
		}
		
	}
	/**
	 * Moves the entity to the left
	 */
	public void left();
	
	/**
	 * Moves the entity to the right
	 */
	public void right();
	
	/**
	 * Stop the entity movement
	 */
	public void stop();
	
	/**
	 * Moves the entity up
	 */
	public void jump();
}