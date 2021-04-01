package model;

import model.Utils.Direction;

public class Player implements Entity{
	
	private int posX = 1024/2;
	private int posY = 768/2;
	
	private Direction lastDir = Direction.RIGHT;
	
	public Bullet shoot() {
		return new Bullet(this.posX, this.posY, lastDir);
	}
}
