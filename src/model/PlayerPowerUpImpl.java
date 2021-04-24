package model;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import com.almasb.fxgl.entity.Entity;

import components.ComponentUtils;
import components.TextureComponent;
import javafx.util.Duration;

/**
 * 
 * this class implements {@link PlayerPowerUp}
 * this class is used to change some player's values
 */
public class PlayerPowerUpImpl implements PlayerPowerUp{
	
	final private Entity player;
	
	/**
	 * this constructor takes a reference to the player entity
	 * @param playerSettings
	 */
	public PlayerPowerUpImpl(final Entity playerSettings) {
		this.player = playerSettings;
	}

	@Override
	public void transformation(final PlayerColor color, final int speed, final int health, final int jumps) {
		
		this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setColor(color);
		this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setSpeed(speed);
    	this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setHealt(health);
    	this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().setMaxJumps(jumps);
    	this.player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().resetNJumps();
    	
    	this.changeTexture(color);
   	}
	
	/**
	 * This method switches the current texture with the texture passed
	 * @param color
	 */
	private void changeTexture (final PlayerColor color) {
		final PlayerTextures playerTexture = new PlayerTextures(color);
		
		getGameTimer().runOnceAfter(() -> {					
			player.removeComponent(ComponentUtils.PLAYERTEXTURE_COMPONENT);  
			player.addComponent(new TextureComponent(playerTexture.getTexture().getTextureMap()));		
		}, Duration.seconds(TextureComponent.TIME_ANIM_DAMAGE));
	}
}
