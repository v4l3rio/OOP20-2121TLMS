package model;

import com.almasb.fxgl.entity.Entity;
import components.ComponentUtils;

public class PlayerPowerUpProxy implements PlayerPowerUp{
	
	private final PlayerPowerUp player;	
	private Entity playerSettings;
	private PlayerColor color;
	
	public PlayerPowerUpProxy(Entity player) {
		this.playerSettings = player;
		this.player = new PlayerPowerUpImpl(this.playerSettings);
		this.color=PlayerColor.BLUE;
	}
	
	private void calledCached(final PlayerColor newColor, final int speed, final int health, final int jumps) {
		if(this.playerSettings.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getColor()!=newColor) {
			//this.color=newColor;
			this.player.transformation(newColor, speed, health, jumps);
		}
	}
	
	public void transformation(final PlayerColor newColor, final int speed, final int health, final int jumps) {
		this.calledCached(newColor, speed, health, jumps);
	}	
	
}
