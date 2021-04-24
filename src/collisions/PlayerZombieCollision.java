package collisions;


import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.getWorldProperties;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.getDialogService;

import java.io.IOException;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import controller.ScoreControllerImpl;
import controller.UserNameControllerImpl;
import model.TLMSType;
import model.score.JSonScoreBuilder;
import model.score.JsonScore;
import javafx.util.Duration;
import model.PlayerPowerUp;
import model.PlayerColor;
import model.PlayerPowerUpProxy;
import components.TextureComponent;

/**
 * Manages collisions between players and zombies
 */
public class PlayerZombieCollision extends CollisionHandler{
	
	private final int BLUE_SPEED = 450;
	private final int BLUE_NUMBER_OF_JUMPS = 1;

	public PlayerZombieCollision(final TLMSType player, final TLMSType zombie) {
		super(player, zombie);
	}

	@Override
	public void onCollisionBegin(final Entity player, final Entity zombie) {
		
		zombie.getComponent(ComponentUtils.TEXTURE_COMPONENT).setAttacking(true);
		player.getComponent(ComponentUtils.PLAYER_COMPONENT).attacked(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
	
		final PlayerPowerUp playerPowerUp = new PlayerPowerUpProxy(player);

    	inc("playerLife", - ((double)zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage()) / 10);			
	
		if(player.getComponent(ComponentUtils.PLAYER_COMPONENT).isDead()) {
			getGameTimer().runOnceAfter(() -> {			
			    	player.removeFromWorld();
					System.out.println("Hai perso!");
					try {
						new ScoreControllerImpl().updateScore(
								new JSonScoreBuilder()
								    .nameFromPath(UserNameControllerImpl.FILE_NAME_USER)
								    .score(getWorldProperties().intProperty("score").get())
								    .build()
					    );
					} catch (IOException e) {
						e.printStackTrace();
					}
					gameOver();		
	    	}, Duration.seconds(TextureComponent.TIME_ANIM_DEATH));
		}		
			playerPowerUp.transformation(PlayerColor.BLUE, BLUE_SPEED, player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getHealt(), BLUE_NUMBER_OF_JUMPS);
	}
	
	private void gameOver() {
        getDialogService().showMessageBox("Game Over. Press OK to exit", getGameController()::exit);
    }
}
