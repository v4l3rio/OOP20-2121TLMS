package model;

/**
 * 
 *	This class contains the main values ​​of the player and the methods to manage them
 */
public class PlayerImpl implements Player {
	private int speed;
	private int jumpCounter;
	private int maxJumps;
	private final int jumpsHeight;
	private int health;
	private int maxHealth;
	private double dimension;
	private PlayerColor color;
	
	/**
	 * this constructor assigns all player variables their initial values
	 * note: I could have declared it as "final" 
	 * but I chose to leave it more open for a hypothetical future extension,
	 * like to change dimension
	 */
	public PlayerImpl() {
		this.dimension = 1.7;
		this.maxHealth = 10;
		this.health = this.maxHealth;
		this.speed = 400;
		this.maxJumps = 1;
		this.jumpCounter = this.maxJumps;
		this.jumpsHeight = -550;
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
		return health;
	}

	@Override
	public void setHealt(final int healt) {
		this.health = healt;
	}
	
	@Override
	public int getMaxHeath() {
		return this.maxHealth;		
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