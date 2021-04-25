package collisions;

import com.almasb.fxgl.physics.CollisionHandler;

import model.TLMSType;
/**
 * It's a factory to manage collisions Player-Gun (prop)
 *
 */
public interface GunCollisionFactory {
	/**
	 * 
	 * @param gun is the new gun to be set
	 * @param gunDuration seconds after which the gun has to be set back to default
	 * @return a new Collision Player-Gun setting the "gun", to be dismounted after "gunDuration" seconds
	 */
	public CollisionHandler createGunCollision(TLMSType gunType, double gunDuration);
}