package collisions;


import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.getWorldProperties;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.getDialogService;

import java.io.IOException;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

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

	public PlayerZombieCollision(final TLMSType player, final TLMSType zombie) {
		super(player, zombie);
	}

	@Override
	public void onCollisionBegin(final Entity player, final Entity zombie) {
		
		zombie.getComponent(ComponentUtils.TEXTURE_COMPONENT).setAttacking(true);
		//player.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		player.getComponent(ComponentUtils.PLAYER_COMPONENT).attacked(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
	
		final PlayerPowerUp playerPowerUp = new PlayerPowerUpProxy(player);

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
		
			playerPowerUp.transformation(PlayerColor.BLUE, 400, player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getHealt(), 1);
	}
	
	private void gameOver() {
        getDialogService().showMessageBox("Game Over. Press OK to exit", getGameController()::exit);
    }
}
