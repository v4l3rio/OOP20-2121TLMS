package collisions;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import com.almasb.fxgl.entity.Entity;

import components.FirePowerComponent;
import components.PlayerComponent;
import components.TextureComponent;
import javafx.util.Duration;
import model.PlayerTexture;

public class PlayerFirePowerCollision implements Collision<Entity, Entity>{

	@Override
	public void onCollision(Entity player, Entity firepower) {
		
		
		
		if(!player.getComponent(PlayerComponent.class).isRed()) {
			PlayerTexture playerTexture = new PlayerTexture();
			
			firepower.getComponent(FirePowerComponent.class).collide();
			firepower.removeFromWorld();
			getGameTimer().runOnceAfter(() -> {
				player.getComponent(PlayerComponent.class).toRed();	
				player.removeComponent(TextureComponent.class);  
				player.addComponent(new TextureComponent(playerTexture.getTextureRed().getTextureMap()));		
			}, Duration.seconds(0.8));
			
		}
		
		
		
	}

}
