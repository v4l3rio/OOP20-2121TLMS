package main.model.player;

import com.almasb.fxgl.entity.Entity;

import main.components.ComponentUtils;

/**
 * 
 * this class implements {@link PlayerSpeedStrategy}
 */
public class PlayerSpeed implements PlayerSpeedStrategy {
	
	private final Entity player;
	
	public PlayerSpeed(final Entity playerSettings) {
		this.player = playerSettings;
	}

	@Override
	public int getVelocity() {
		return this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getSpeed();
	}

}
