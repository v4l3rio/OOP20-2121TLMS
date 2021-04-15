package model;

import java.util.Random;


/**
 * 
 * @version 2.1
 * Movement of entity
 */
public interface Moveable {
	
	public static enum DIRECTIONS {
		LEFT, RIGHT, STOP, JUMP
	}
	
	public static enum TYPE_OF_MOVEMENT{
		FOLLOW, RANDOM;
		
		public static TYPE_OF_MOVEMENT getRandom() {
			return TYPE_OF_MOVEMENT.values()[new Random().nextInt(TYPE_OF_MOVEMENT.values().length)];
		}
		
	}
	/**
	 * moves the entity to the left
	 */
	public void left();
	
	/**
	 * moves the entity to the right
	 */
	public void right();
	
	/**
	 * stop the entity movement
	 */
	public void stop();
	
	/**
	 * moves the entity up
	 */
	public void jump();
}