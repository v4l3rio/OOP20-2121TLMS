package collisions;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import components.AnimationComponent;
import components.ComponentUtils;
import javafx.util.Duration;

public class PlayerZombieCollision implements Collision<Entity, Entity>{

	@Override
	public void onCollision(Entity player, Entity zombie) {
		
		player.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		
		System.out.println("Il player ha vita: " + player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		
		zombie.getComponent(ComponentUtils.TEXTURE_COMPONENT).setAttack(true);		
		
		player.getComponent(AnimationComponent.class).attacked();
		
		getGameTimer().runOnceAfter(() -> {
			if(player.getComponent(AnimationComponent.class).isDead()) {
		    	player.removeFromWorld();
				System.out.println("Hai perso!");
				System.exit(0);
			}
    	}, Duration.seconds(1.7));		
		
	}

}
