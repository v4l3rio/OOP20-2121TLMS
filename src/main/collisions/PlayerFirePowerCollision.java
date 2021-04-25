package main.collisions;

import static com.almasb.fxgl.dsl.FXGL.set;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import main.components.ComponentUtils;
import main.model.TLMSType;
import main.model.player.PlayerColor;
import main.model.player.PlayerPowerUp;
import main.model.player.PlayerPowerUpProxy;

/**
 * Manages collisions between players and power-ups
 */
public class PlayerFirePowerCollision extends CollisionHandler{
	
	private static final int RED_SPEED = 650;
	private static final int RED_NUMBER_OF_JUMPS = 5;
	

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


