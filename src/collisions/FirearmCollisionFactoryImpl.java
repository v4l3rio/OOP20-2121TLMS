package collisions;

import static com.almasb.fxgl.dsl.FXGL.runOnce;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import components.ComponentUtils;
import components.FirearmComponent;
import factories.FirearmFactory;
import factories.FirearmFactoryImpl;
import javafx.util.Duration;
import model.TLMSType;

public class FirearmCollisionFactoryImpl implements FirearmCollisionFactory {

	@Override
	public CollisionHandler createGunCollision(TLMSType firearmType, int delay) {
		return new CollisionHandler(TLMSType.PLAYER, firearmType) {

			@Override
			public void onCollision(Entity player, Entity firearmProp) {
				FirearmFactory factory = new FirearmFactoryImpl();
				FirearmComponent firearmComponent = player.getComponent(ComponentUtils.FIREARM_COMPONENT);
				//if current gun isn't the default one, there's another timer running,
				//this way I have him know there has been a multiple gun change, so that its timer
				//has to be disabled.
				//this prevents timers from piling up
				if(!firearmComponent.isDefault()) {
					firearmComponent.setChanged(true);
				}
				//I set MachineGun to be the new gun
				firearmComponent.setCurrentFirearm(firearmType.equals(TLMSType.MACHINEGUN) ? factory.createMachineGun()
						: factory.createMagmaGun());
				//I remove the prop
				firearmProp.removeFromWorld();
				//after a delay, set the gun back to default, but if there has been a multiple change, do nothing
				//but resetting the boolean
				runOnce(()->{
					//FirearmComponent firearmComponentDelayed = player.getComponent(ComponentUtils.FIREARM_COMPONENT);
					if(firearmComponent.hasChanged()) {
						firearmComponent.setChanged(false);
					} else {
						firearmComponent.setCurrentFirearm(firearmComponent.getDefaultFirearm());
					}
				}, Duration.seconds(delay));
			}
		};
	}

}
