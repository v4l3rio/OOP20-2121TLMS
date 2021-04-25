package collisions;


import static com.almasb.fxgl.dsl.FXGL.*;

import java.io.IOException;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import application.TheLastManStandingApp;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import components.TextureComponent;
import components.ComponentUtils;
import controller.ScoreControllerImpl;
import model.TLMSType;
import model.score.JsonScore;
import javafx.util.Duration;
import model.PlayerPowerUp;
import model.PlayerColor;
import model.PlayerPowerUpProxy;
import model.PlayerTextures;

/**
 * Manages collisions between players and zombies
 */
public class PlayerZombieCollision extends CollisionHandler{

	public PlayerZombieCollision(TLMSType player, TLMSType zombie) {
		super(player, zombie);
	}

	@Override
	public void onCollisionBegin(Entity player, Entity zombie) {
		
		zombie.getComponent(ComponentUtils.TEXTURE_COMPONENT).setAttacking(true);
		//player.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		player.getComponent(ComponentUtils.PLAYER_COMPONENT).attacked(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
	
		PlayerPowerUp playerPowerUp = new PlayerPowerUpProxy(player);

    	inc("playerLife", - ((double)zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage()) / 10);			
	
		if(player.getComponent(ComponentUtils.PLAYER_COMPONENT).isDead()) {
			getGameTimer().runOnceAfter(() -> {			
			    	player.removeFromWorld();
					System.out.println("Hai perso!");
					try {
						new ScoreControllerImpl().updateScore(
								new JsonScore.Builder()
								    .nameFromPath(ScoreControllerImpl.FILE_NAME_USER)
								    .score(getWorldProperties().intProperty("score").get())
								    .build()
					    );
					} catch (IOException e) {
						e.printStackTrace();
					}
					gameOver();		
	    	}, Duration.seconds(1.7));
		}
		
		if(player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getColor()==PlayerColor.RED) {
			playerPowerUp.transformation(PlayerColor.BLUE, 400, player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getHealt(), 1);
			
			getGameTimer().runOnceAfter(() -> {
				PlayerTextures playerTextures = new PlayerTextures(PlayerColor.BLUE);
				player.removeComponent(ComponentUtils.PLAYERTEXTURE_COMPONENT);  
				player.addComponent(new TextureComponent(playerTextures.getTexture().getTextureMap()));
			}, Duration.seconds(0.8));
		}
	}
	
	private void gameOver() {
        getDialogService().showMessageBox("Game Over. Press OK to exit", getGameController()::exit);
    }
}
