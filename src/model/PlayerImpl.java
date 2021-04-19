package model;

public class PlayerImpl implements Player {
	private int speed;
	private int jumpCounter;
	private int maxJumps;
	private final int jumpsHeight;
	private int healt;
	private double dimension;
	private PlayerColor color;
	
	public PlayerImpl() {
		this.dimension = 1.7;
		this.healt = 10;
		this.speed = 400;
		this.maxJumps = 1;
		this.jumpCounter = 1;
		this.jumpsHeight = -600;
		this.color = PlayerColor.BLUE;
	}
	
	@Override
	public int getSpeed() {		
		return this.speed;
	}
	
	@Override
	public void setSpeed(final int speed) {
		this.speed = speed;
	}
	
	@Override
	public int getHealt() {
		return healt;
	}

	@Override
	public void setHealt(final int healt) {
		this.healt = healt;
	}

	@Override
	public int getNJumps () {
		return this.jumpCounter;
	}
	
	@Override
	public void setMaxJumps(final int maxJumps) {
		this.maxJumps = maxJumps;		
	}

	@Override
	public void resetNJumps () {
			this.jumpCounter = this.maxJumps;
	}
	
	@Override
	public void decreaseJumps() {
		this.jumpCounter--;
	}
	
	@Override
	public int getJumpHeight() {
		return this.jumpsHeight;		
	}

	@Override
	public double getDimension() {
		return this.dimension;
	}
	
	@Override
	public PlayerColor getColor() {
		return this.color;
	}
	
	@Override
	public void setColor(final PlayerColor color) {
		this.color = color;
	}
	
	
}