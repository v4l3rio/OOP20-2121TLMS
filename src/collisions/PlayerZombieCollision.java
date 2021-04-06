package collisions;

import com.almasb.fxgl.entity.Entity;

import components.ComponentUtils;

public class PlayerZombieCollision implements Collision<Entity, Entity>{

	@Override
	public void onCollision(Entity player, Entity zombie) {
		
		player.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		
		System.out.println("Il player ha vita: " + player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		
		zombie.getComponent(ComponentUtils.TEXTURE_COMPONENT).setAttack(true);
		
		if(player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
			//player muore implementare la morte del player
		}
		
	}

}
