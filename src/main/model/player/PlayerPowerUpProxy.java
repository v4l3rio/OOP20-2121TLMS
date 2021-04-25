package main.model.player;

import com.almasb.fxgl.entity.Entity;
import main.components.ComponentUtils;

/**
 * 
 * this class implements {@link PlayerPowerUp}
 * this class uses the Proxy's pattern
 */
public class PlayerPowerUpProxy implements PlayerPowerUp{
	
	private final PlayerPowerUp player;	
	private final Entity playerSettings;
	
	/**
	 * 
	 * @param player entity
	 */
	public PlayerPowerUpProxy(final Entity player) {
		this.playerSettings = player;
		this.player = new PlayerPowerUpImpl(this.playerSettings);
	}
	
	/**
	 * this class is used as an access control
	 * @param newColor
	 * @param speed
	 * @param health
	 * @param jumps
	 */
	private void calledCached(final PlayerColor newColor, final int speed, final int health, final int jumps) {
		if(this.playerSettings.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getColor()!=newColor) {
			this.player.transformation(newColor, speed, health, jumps);
		}
	}
	
	public void transformation(final PlayerColor newColor, final int speed, final int health, final int jumps) {
		this.calledCached(newColor, speed, health, jumps);
	}	
	
}
