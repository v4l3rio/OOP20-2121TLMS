package collisions;


import com.almasb.fxgl.entity.Entity;

import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import model.TLMSType;

public class ZombieWallCollision extends CollisionHandler{

	public ZombieWallCollision(TLMSType zombie , TLMSType wall) {
		super(zombie, wall);
		
	}
	
	@Override
	public void onCollision(Entity zombie, Entity wall) {		
		if(zombie.hasComponent(ComponentUtils.RANDOM_MOVEMENT_COMPONENT)) {
			zombie.getComponent(ComponentUtils.RANDOM_MOVEMENT_COMPONENT).jump();		
		}
		else {
			zombie.getComponent(ComponentUtils.FOLLOW_MOVEMENT_COMPONENT).jump();
		}
		
	}

}
