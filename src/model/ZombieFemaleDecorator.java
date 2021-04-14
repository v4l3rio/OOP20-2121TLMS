package model;

import static model.TLMSType.*;


import java.util.UUID;

/**
 * 
 * @author Valerio Di Zio
 * @version 2.0
 * This Class models a Female Zombie 
 */
public class ZombieFemaleDecorator implements ZombieTextureDecorator{

	private Zombie zombie;
	private Texture texture;

	/**
	 * 
	 * @param model of zombie
	 */
	public ZombieFemaleDecorator(Zombie zombie) {
		this.zombie = zombie;
		this.texture = new Texture();

		this.texture.addTexture(IDLE, "assets/textures/zombie/zombie1/zombie_idle.png");
		this.texture.addTexture(WALK, "assets/textures/zombie/zombie1/zombie_walk.png");
		this.texture.addTexture(DEAD, "assets/textures/zombie/zombie1/zombie_dead.png");
		this.texture.addTexture(ATTACK, "assets/textures/zombie/zombie1/zombie_attack.png");
	}

	public void setLife(int life) {
		this.zombie.setLife(life);
	}

	public UUID getUUID() {
		return this.zombie.getUUID();
	}


	public int getSpeed() {
		
		return this.zombie.getSpeed();
	}


	public int getLife() {
		
		return this.zombie.getLife();
	}
	
	/**
	 * 
	 * @return Texture of this zombie
	 */
	public Texture getTexture() {
		return this.texture;
	}

	@Override
	public void setDamage(int dmg) {
		this.zombie.setDamage(dmg);
		
	}

	@Override
	public int getDamage() {
		return this.zombie.getDamage();
	}
	
	
	
}
