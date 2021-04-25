package collisions;

import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import components.TextureComponent;
import javafx.util.Duration;
import model.PlayerColor;
import model.PlayerPowerUp;
import model.PlayerPowerUpProxy;
import model.PlayerTextures;
import model.TLMSType;

public class PlayerFirePowerCollision extends CollisionHandler{
	

	public PlayerFirePowerCollision(TLMSType player, TLMSType firePower) {
		super(player, firePower);
	}

	@Override
	public void onCollisionBegin(Entity player, Entity firepower) {
		
		firepower.removeFromWorld();
		PlayerTextures playerTexture = new PlayerTextures(PlayerColor.RED);
		PlayerPowerUp playerPowerUp = new PlayerPowerUpProxy(player);
		set("playerLife", 1.0);
		
		if(player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getColor()==PlayerColor.BLUE) {
			playerPowerUp.transformation(PlayerColor.RED, 650, 
					player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getMaxHeath(), 5);
				getGameTimer().runOnceAfter(() -> {					
					player.removeComponent(ComponentUtils.PLAYERTEXTURE_COMPONENT);  
					player.addComponent(new TextureComponent(playerTexture.getTexture().getTextureMap()));		
				}, Duration.seconds(0.8));
		
			}
			
		}
	
	}


