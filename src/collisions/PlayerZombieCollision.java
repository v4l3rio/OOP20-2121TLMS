package collisions;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import com.almasb.fxgl.entity.Entity;
import components.PlayerComponent;
import components.TextureComponent;
import components.ComponentUtils;
import javafx.util.Duration;
import model.PlayerTexture;

public class PlayerZombieCollision implements Collision<Entity, Entity>{

	@Override
	public void onCollision(Entity player, Entity zombie) {
		
		player.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		
		PlayerTexture playerTexture = new PlayerTexture();
		
		if(player.getComponent(PlayerComponent.class).isRed()) {
			getGameTimer().runOnceAfter(() -> {
				player.removeComponent(TextureComponent.class);  
				player.addComponent(new TextureComponent(playerTexture.getTextureBlue().getTextureMap()));		
			}, Duration.seconds(0.8));
		}
    	
    	player.getComponent(PlayerComponent.class).attacked();
		
		
		System.out.println("Il player ha vita: " + player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		
		zombie.getComponent(ComponentUtils.TEXTURE_COMPONENT).setAttack(true);		
	
		if(player.getComponent(ComponentUtils.PLAYER_COMPONENT).isDead()) {
			getGameTimer().runOnceAfter(() -> {			
			    	player.removeFromWorld();
					System.out.println("Hai perso!");
					System.exit(0);				
	    	}, Duration.seconds(1.7));
		}
		
	}

}
