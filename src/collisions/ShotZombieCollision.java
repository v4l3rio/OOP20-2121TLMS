
package collisions;


import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;
import static com.almasb.fxgl.dsl.FXGL.*;
import components.ComponentUtils;
import model.TLMSType;

/**
 * Manages collisions between bullet and zombies
 */

public class ShotZombieCollision extends CollisionHandler{


	public ShotZombieCollision(TLMSType shot , TLMSType zombie) {
		super(shot, zombie);

	}
	@Override
	public void onCollisionBegin(Entity shot, Entity zombie) {		
		zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(shot.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		
		shot.removeFromWorld();
		
		System.out.println("Lo zombie ha vita: " + zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		inc("score", +1);
		spawn("zombiePoints", new SpawnData(zombie.getX(),zombie.getY()).put("zombiePoints", "+1"));

		if(zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
			System.out.println("Zombie rimosso");
		}
		
	}

}
