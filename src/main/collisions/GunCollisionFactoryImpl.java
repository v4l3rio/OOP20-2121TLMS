package main.collisions;

import static com.almasb.fxgl.dsl.FXGL.runOnce;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import main.components.ComponentUtils;
import main.components.GunComponent;
import main.factories.TexturedGunFactoryImpl;
import javafx.util.Duration;
import main.model.TLMSType;

/**
 * Implements a collision factory to assign a new gun to the player,
 * when colliding with a new gun prop.
 */
public class GunCollisionFactoryImpl implements GunCollisionFactory {
	
	/**
	 * creates a collisionHandler from gun type, setting the new gun for a delay time.
	 */
	@Override
	public final CollisionHandler createGunCollision(final TLMSType gunType, final  double delay) {
		return new CollisionHandler(TLMSType.PLAYER, gunType) {
			/**
			 * manages behavior to be executed on collision
			 */
			@Override
			public void onCollision(final Entity player, final Entity gunProp) {
				final GunComponent gunComponent = player.getComponent(ComponentUtils.GUN_COMPONENT);
				//if current gun isn't the default one, there's another timer running,
				//this way I have him know there has been a multiple gun change, so that its timer
				//has to be disabled.
				//this prevents timers from piling up
				if (!gunComponent.isDefault()) {
					gunComponent.setChanged(true);
				}
				//I set MachineGun to be the new gun
				gunComponent.setCurrentGun(new TexturedGunFactoryImpl().getTexturedGun(gunType));
				//I remove the prop
				gunProp.removeFromWorld();
				//after a delay, set the gun back to default, but if there has been a multiple change, do nothing
				//but resetting the boolean
				runOnce(() -> {
					if (gunComponent.isChanged()) {
						gunComponent.setChanged(false);
					} else {
						gunComponent.setCurrentGun(gunComponent.getDefaultGun());
					}
				}, Duration.seconds(delay));
			}
		};
	}

}
