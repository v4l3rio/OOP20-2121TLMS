package collisions;

import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import model.PlayerColor;
import model.PlayerPowerUp;
import model.PlayerPowerUpProxy;
import model.TLMSType;

/**
 * Manages collisions between players and power-ups
 */
public class PlayerFirePowerCollision extends CollisionHandler{
	
	private final int RED_SPEED = 650;
	private final int RED_NUMBER_OF_JUMPS = 5;
	

	public PlayerFirePowerCollision(final TLMSType player, final TLMSType firePower) {
		super(player, firePower);
	}

	@Override
	public void onCollisionBegin(final Entity player, final Entity firepower) {
		
		firepower.removeFromWorld();
		final PlayerPowerUp playerPowerUp = new PlayerPowerUpProxy(player);
		set("playerLife", 1.0);
		
			playerPowerUp.transformation(PlayerColor.RED, RED_SPEED, 
					player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getMaxHeath(), RED_NUMBER_OF_JUMPS);			
		}
	
	}


