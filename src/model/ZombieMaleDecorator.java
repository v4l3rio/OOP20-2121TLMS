package model;

import static model.TLMSType.*;

import java.util.UUID;

public class ZombieMaleDecorator implements Enemy, ZombieTextureDecorator{

	private Zombie zombie;
	private Texture texture;
	
	
	public ZombieMaleDecorator(Zombie zombie) {
		this.zombie = zombie;
		this.texture = new Texture();
		
		this.texture.addTexture(IDLE, "assets/textures/zombie/zombie0/zombie_idle.png");
		this.texture.addTexture(WALK, "assets/textures/zombie/zombie0/zombie_walk.png");
		this.texture.addTexture(DEAD, "assets/textures/zombie/zombie0/zombie_dead.png");
		this.texture.addTexture(ATTACK, "assets/textures/zombie/zombie0/zombie_attack.png");
	}

	public void setHp(int hp) {
		this.zombie.setHp(hp);
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
