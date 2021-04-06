package collisions;


import com.almasb.fxgl.entity.Entity;

import components.ComponentUtils;
import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.concurrent.TimeUnit;

import com.almasb.fxgl.achievement.AchievementEvent;


public class BulletZombieCollision implements Collision<Entity, Entity>{


	public void onCollision(Entity bullet, Entity zombie) {
		
		zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(bullet.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		
		bullet.removeFromWorld();
		
		System.out.println("Lo zombie ha vita: " + zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		
		//zombie.getComponent(ComponentUtils.TEXTURE_COMPONENT).setAttack(true);
		
		if(zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
			System.out.println("Ho eliminato lo zombie!");
		}
		
	}

}
