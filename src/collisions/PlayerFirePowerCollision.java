package collisions;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import components.FirePowerComponent;
import components.PlayerComponent;
import components.TextureComponent;
import javafx.util.Duration;
import model.PlayerImpl;
import model.Player;
import model.PlayerColor;
import model.PlayerPowerUp;
import model.PlayerPowerUpProxy;
import model.PlayerTexture;
import model.TLMSType;

public class PlayerFirePowerCollision extends CollisionHandler{
	

	public PlayerFirePowerCollision(TLMSType player, TLMSType firePower) {
		super(player, firePower);
	}

	@Override
	public void onCollisionBegin(Entity player, Entity firepower) {
		
		firepower.removeFromWorld();
		PlayerTexture playerTexture = new PlayerTexture();
		PlayerPowerUp playerPowerUp = new PlayerPowerUpProxy(player);
		
		if(player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getColor()==PlayerColor.BLUE) {
			playerPowerUp.transformation(PlayerColor.RED, 650, 10, 5);
				getGameTimer().runOnceAfter(() -> {					
					player.removeComponent(ComponentUtils.PLAYERTEXTURE_COMPONENT);  
					player.addComponent(new TextureComponent(playerTexture.getTextureRed().getTextureMap()));		
				}, Duration.seconds(0.8));
				
			
			}
			
		}
		
		
		
	}


