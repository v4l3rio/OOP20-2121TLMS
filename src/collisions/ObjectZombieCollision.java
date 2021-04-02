package collisions;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;

import components.DamagingComponent;


public class ObjectZombieCollision implements Collision<Entity, Entity>{


	@Override
	public void onCollision(Entity obj, Entity zombie) {
		
		zombie.getComponent(HealthIntComponent.class).damage(obj.getComponent(DamagingComponent.class).getDamage());
		
		System.out.println("Lo zombie ha vita: " + zombie.getComponent(HealthIntComponent.class).getValue());
		
		
		if(zombie.getComponent(HealthIntComponent.class).getValue()<=0) {
			zombie.removeFromWorld();
			System.out.println("Ho eliminato lo zombie!");
		}
		
	}

}
