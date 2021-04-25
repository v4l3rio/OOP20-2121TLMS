package main.collisions;


import com.almasb.fxgl.entity.Entity;

import com.almasb.fxgl.physics.CollisionHandler;

import main.components.ComponentUtils;
import main.model.TLMSType;

/**
 * Class used to manage the collision between a zombie and a wall, jump to climb over it
 */
public class ZombieWallCollision extends CollisionHandler{

	/**
	 * @param zombie 
	 * @param wall
	 */
	public ZombieWallCollision(final TLMSType zombie , final TLMSType wall) {
		super(zombie, wall);
		
	}
	
	@Override
	public void onCollision(final Entity zombie, final Entity wall) {		
		if(zombie.hasComponent(ComponentUtils.RANDOM_MOVEMENT_COMPONENT)) {
			zombie.getComponent(ComponentUtils.RANDOM_MOVEMENT_COMPONENT).jump();		
		}
		else {
			zombie.getComponent(ComponentUtils.FOLLOW_MOVEMENT_COMPONENT).jump();
		}
		
	}

}
