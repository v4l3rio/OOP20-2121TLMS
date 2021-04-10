package model;

public class Player {
	
//	private boolean redPlayer;

	private int lives;
	private int speed;
	private int nJumps;
	private int jumpsHeight;
	
	
	public Player() {
//		this.redPlayer = false;
		this.lives = 2;
		this.speed = 200;
		this.nJumps = 1;
		this.jumpsHeight = -400;
	}
	
	public int getSpeed() {		
		return this.speed;
	}
	
	public int getNJumps () {
		return this.nJumps;
	}
	
	public void resetNJumps () {
//		if(!redPlayer) {
//			this.nJumps = 1;
//		} else {
			this.nJumps = 1;
//		}
	}
	
	public void decreaseJumps() {
		this.nJumps--;
	}
	
	public int getJumpHeight() {
		return this.jumpsHeight;		
	}

	public int getLife() {		
		return this.lives;
	}
	
	public void setLife(int life) {
		this.lives = life;
	}
	
	public void decreaseHealth() {
		this.lives--;
//		if (isRedPlayer()) {
//			this.toBlue();
//		}
	}
	
//	public boolean isRedPlayer() {
//		return this.redPlayer;
//	}
	
//	public void toRed () {
//		this.redPlayer = true;
//		this.lives = 3;
//		this.speed = 300;
//		this.nJumps = 2;
//		this.jumpsHeight = -500;
//	}
//	
//	public void toBlue() {
//		this.redPlayer = false;
//		this.speed = 200;
//		this.nJumps = 1;
//		this.jumpsHeight = -400;
//	}
}