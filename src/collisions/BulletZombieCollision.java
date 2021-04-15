package collisions;


import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import model.TLMSType;



/**
 * @version 2.3
 * Manages collisions between bullet and zombies
 */

public class BulletZombieCollision extends CollisionHandler{


	
	public BulletZombieCollision(TLMSType bullet, TLMSType zombie) {
		super(bullet, zombie);

	}

	public void onCollisionBegin(Entity bullet, Entity zombie) {
		
		zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(bullet.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		
		bullet.removeFromWorld();
		
		System.out.println("Lo zombie ha vita: " + zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		

		if(zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
			System.out.println("Zombie rimosso");
		}
		
	}

}
