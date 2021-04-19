package model;

import com.almasb.fxgl.entity.Entity;

import components.ComponentUtils;

public class PlayerPowerUpImpl implements PlayerPowerUp{
	
	private Entity player;
	
	public PlayerPowerUpImpl(Entity playerSettings) {
		this.player = playerSettings;
	}

	@Override
	public void transformation(final PlayerColor color, int speed, int health, int jumps) {
		//System.out.println(this.player.getColor() + " " + this.player.getNJumps());
		
		this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setColor(color);
		this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setSpeed(speed);
    	this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setHealt(health);
    	this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setMaxJumps(jumps);
    	this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().resetNJumps();
    	
    	//System.out.println(this.player.getColor() + " " + this.player.getNJumps());
	}
}
