package model;

public class Bullet extends Entity implements Moveable{
	
	private int posX;
	private int posY;
	private static final int SIZE = 100;
	
	private Direction dir;
	
	public Bullet(int x, int y, Direction dir) {
		this.posX = x;
		this.posY = y;
		this.dir = dir;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public static int getSize() {
		return SIZE;
	}

	public Direction getDir() {
		return dir;
	}
}
