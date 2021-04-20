package model;

import com.almasb.fxgl.entity.Entity;

import components.ComponentUtils;

/**
 * 
 * this class implements {@link PlayerPowerUp}
 * this class is used to change some player's values
 */
public class PlayerPowerUpImpl implements PlayerPowerUp{
	
	private Entity player;
	
	/**
	 * this constructor takes a reference to the player entity
	 * @param playerSettings
	 */
	public PlayerPowerUpImpl(Entity playerSettings) {
		this.player = playerSettings;
	}

	@Override
	public void transformation(final PlayerColor color, int speed, int health, int jumps) {
		
		this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setColor(color);
		this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setSpeed(speed);
    	this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setHealt(health);
    	this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setMaxJumps(jumps);
    	this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().resetNJumps();
    	
   	}
}
