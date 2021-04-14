package collisions;


import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import model.TLMSType;

import static com.almasb.fxgl.dsl.FXGL.*;



public class BulletZombieCollision extends CollisionHandler{


	public BulletZombieCollision(TLMSType bullet, TLMSType zombie) {
		super(bullet, zombie);
	}

	public void onCollision(Entity bullet, Entity zombie) {
		
		zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).damage(bullet.getComponent(ComponentUtils.DAMAGING_COMPONENT).getDamage());
		
		bullet.removeFromWorld();
		
		System.out.println("Lo zombie ha vita: " + zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue());
		
	
		
		if(zombie.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
			System.out.println("Zombie rimosso");
		}
		
	}

}
