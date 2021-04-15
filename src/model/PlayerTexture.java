package model;

public class PlayerTexture {
	
	private Texture playerTextureBlue;
	private Texture playerTextureRed;
	
	public PlayerTexture () {
		this.playerTextureBlue = new Texture();
		this.playerTextureRed = new Texture();
		
		this.playerTextureBlue.addTexture(TLMSType.IDLE, "Gunner_Blue_Idle.png");
		this.playerTextureBlue.addTexture(TLMSType.RUN, "Gunner_Blue_Run.png");
		this.playerTextureBlue.addTexture(TLMSType.JUMP, "Gunner_Blue_Jump.png");
		this.playerTextureBlue.addTexture(TLMSType.DEAD, "Gunner_Blue_Death.png");	
		
		this.playerTextureRed.addTexture(TLMSType.IDLE, "Gunner_Red_Idle.png");
		this.playerTextureRed.addTexture(TLMSType.RUN, "Gunner_Red_Run.png");
		this.playerTextureRed.addTexture(TLMSType.JUMP, "Gunner_Red_Jump.png");
		this.playerTextureRed.addTexture(TLMSType.DEAD, "Gunner_Red_Death.png");		
	}
	
	public Texture getTextureBlue() {
		return this.playerTextureBlue;
	}
	
	public Texture getTextureRed() {
		return this.playerTextureRed;
	}
}
