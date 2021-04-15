package collisions;


import com.almasb.fxgl.dsl.FXGL;
import static com.almasb.fxgl.dsl.FXGL.*;

import java.io.IOException;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import controller.ScoreControllerImpl;
import model.TLMSType;
import model.score.JsonScore;

/**
 * @version 2.2
 * Manages collisions between players and zombies
 */

public class PlayerZombieCollision extends CollisionHandler{

	public PlayerZombieCollision(TLMSType player, TLMSType zombie) {
		super(player, zombie);
	}

	@Override
	public void onCollisionBegin(Entity player, Entity zombie) {
		
		player.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(zombie.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		
		System.out.println("Il player ha vita: " + player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		
		zombie.getComponent(ComponentUtils.TEXTURE_COMPONENT).setAttacking(true);
		
		inc("playerLife", -0.1);
		
		if(player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
			
			//player muore implementare la morte del player
			
			System.out.println("Hai perso!");
			player.removeFromWorld();
			try {
				new ScoreControllerImpl().updateScore(
						new JsonScore(getWorldProperties().intProperty("score").get())
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
			gameOver();
		}
	}
	
	private void gameOver() {
        getDialogService().showMessageBox("Game Over. Press OK to exit", getGameController()::exit);
    }
}
