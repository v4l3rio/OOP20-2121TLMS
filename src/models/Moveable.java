package models;

public interface Moveable {
	
	public static enum DIRECTIONS {
		LEFT, RIGHT, STOP, JUMP
	}
	
	public void left();
	public void right();
	public void stop();
	public void jump();
}
