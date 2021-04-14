package collisions;


import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import model.TLMSType;

/**
 * @version 2.2
 * Manages collisions between players and zombies
 */

public class PlayerZombieCollision extends CollisionHandler{

	public PlayerZombieCollision(TLMSType player, TLMSType zombie) {
		super(player, zombie);
	}

	@Override
	public void onCollisionBegin(Entity player, Entity zombie) {
		
		player.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		
		System.out.println("Il player ha vita: " + player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		
		zombie.getComponent(ComponentUtils.TEXTURE_COMPONENT).setAttacking(true);
		
		if(player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
			
			//player muore implementare la morte del player
			
			System.out.println("Hai perso!");
			player.removeFromWorld();
			System.exit(0);
		}
		
	}

}
