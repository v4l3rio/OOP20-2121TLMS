package model;

import static model.TLMSType.IDLE;
import static model.TLMSType.WALK;
import static model.TLMSType.DEAD;
import static model.TLMSType.ATTACK;

import java.util.UUID;


/**
 * This Class models a Male Zombie 
 */
public class ZombieMaleDecorator implements ZombieTextureDecorator{

	private final Zombie zombie;
	private final Texture texture;
	private static final int DAMAGE_BONUS = 1;
	
	/**
	 * @param model of zombie
	 */
	public ZombieMaleDecorator(final Zombie zombie) {
		this.zombie = zombie;
		this.texture = new Texture();
	
		this.texture.addTexture(IDLE, "assets/textures/zombie/zombie0/zombie_idle.png");
		this.texture.addTexture(WALK, "assets/textures/zombie/zombie0/zombie_walk.png");
		this.texture.addTexture(DEAD, "assets/textures/zombie/zombie0/zombie_dead.png");
		this.texture.addTexture(ATTACK, "assets/textures/zombie/zombie0/zombie_attack.png");
	}
	
	public String getMovementStrategy() {
		return this.zombie.getMovementStrategy();
	}
	
	
	@Override
	public void setLife(final int life) {
		this.zombie.setLife(life);
	}

	@Override
	public void setSpeed(final int spd) {
		this.zombie.setSpeed(spd);
		
	}

	@Override
	public void setDamage(final int dmg) {
		this.zombie.setDamage(dmg);
		
	}

	@Override
	public UUID getUUID() {
		return this.zombie.getUUID();
	}

	@Override
	public int getSpeed() {		
		return this.zombie.getSpeed();
	}

	@Override
	public int getLife() {		
		return this.zombie.getLife();
	}

	@Override
	public Texture getTexture() {
		return this.texture;
	}

	@Override
	public int getDamage() {
		return this.zombie.getDamage() + DAMAGE_BONUS;
	}
	
	
	
}
