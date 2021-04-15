package collisions;

import com.almasb.fxgl.entity.Entity;
import model.Firearm;
import model.TLMSType;

public interface FirearmCollisionFactory {
	/**
	 * 
	 * @param gun the new gun to be set
	 * @param gunDuration seconds after which the gun has to be set back to default
	 * @return a new Collision Player-Gun setting the "gun", to be dismounted after "gunDuration" seconds
	 */
	public Collision<Entity, Entity> createGunCollision(TLMSType gunType, int gunDuration);
}