package collisions;

import static com.almasb.fxgl.dsl.FXGL.runOnce;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import components.GunComponent;
import factories.GunFactoryImpl;
import javafx.util.Duration;
import model.TLMSType;
/**
 * Implements a collision factory to assign a new gun to the player, when colliding with a new gun prop
 */
public class GunCollisionFactoryImpl implements GunCollisionFactory {
	/**
	 * creates a collisionHandler from gun type, setting the new gun for a delay time
	 */
	@Override
	public CollisionHandler createGunCollision(TLMSType gunType, double delay) {
		return new CollisionHandler(TLMSType.PLAYER, gunType) {
			/**
			 * manages behavior to be executed on collision
			 */
			@Override
			public void onCollision(Entity player, Entity gunProp) {
				GunComponent gunComponent = player.getComponent(ComponentUtils.GUN_COMPONENT);
				//if current gun isn't the default one, there's another timer running,
				//this way I have him know there has been a multiple gun change, so that its timer
				//has to be disabled.
				//this prevents timers from piling up
				if(!gunComponent.isDefault()) {
					gunComponent.setChanged(true);
				}
				//I set MachineGun to be the new gun
				gunComponent.setCurrentGun(new GunFactoryImpl().gunFromType(gunType));
				//I remove the prop
				gunProp.removeFromWorld();
				//after a delay, set the gun back to default, but if there has been a multiple change, do nothing
				//but resetting the boolean
				runOnce(()->{
					if(gunComponent.isChanged()) {
						gunComponent.setChanged(false);
					} else {
						gunComponent.setCurrentGun(gunComponent.getDefaultGun());
					}
				}, Duration.seconds(delay));
			}
		};
	}

}
