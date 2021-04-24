package model;

/**
 * 
 *	this class assigns different textures to different types
 */
public class PlayerTextures {
	
	private Texture playerTexture;
	
	/**
	 * 
	 * @param player's color
	 */
	public PlayerTextures(final PlayerColor color) {
		this.playerTexture = new Texture();
		
		this.playerTexture.addTexture(TLMSType.IDLE, "Gunner_" + color.getActualName() + "_Idle.png");
		this.playerTexture.addTexture(TLMSType.RUN, "Gunner_" + color.getActualName() + "_Run.png");
		this.playerTexture.addTexture(TLMSType.JUMP, "Gunner_" + color.getActualName() + "_Jump.png");
		this.playerTexture.addTexture(TLMSType.DEAD, "Gunner_" + color.getActualName() +"_Death.png");			
	}
	
	/**
	 * 
	 * @return playerTexture
	 */
	public Texture getTexture() {
		return this.playerTexture;
	}
}
