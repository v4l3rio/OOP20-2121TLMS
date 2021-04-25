
package main.collisions;


import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.almasb.fxgl.dsl.FXGL.inc;
import main.components.ComponentUtils;
import main.model.TLMSType;

/**
 * Manages collisions between bullet and zombies
 */

public class ShotZombieCollision extends CollisionHandler{
	
	public ShotZombieCollision(final TLMSType shot, final TLMSType zombie) {
		super(shot, zombie);
	}
	
	@Override
	public final void onCollisionBegin(final Entity shot, final Entity zombie) {
		zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(shot.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
	
		shot.removeFromWorld();
	
		System.out.println("Lo zombie ha vita: " + zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		inc("score", +1);
		spawn("zombiePoints", new SpawnData(zombie.getX(), zombie.getY()).put("zombiePoints", "+1"));

		if (zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue() <= 0) {
			inc("score", +zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getMaxValue());
			System.out.println("Zombie rimosso");
		}
	
	}

}
