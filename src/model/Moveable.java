package model;

public interface Moveable {
	
	public static enum DIRECTIONS {
		LEFT, RIGHT, STOP, JUMP
	}
	
	public static enum TYPEOFMOVEMENT{
		FOLLOW, RANDOM
	}
	
	public void left();
	public void right();
	public void stop();
	public void jump();
}
