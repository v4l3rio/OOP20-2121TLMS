package collisions;

import static com.almasb.fxgl.dsl.FXGL.runOnce;

import com.almasb.fxgl.entity.Entity;
import components.ComponentUtils;
import javafx.util.Duration;
import model.Firearm;
import model.MagmaGun;


public class PlayerMagmaGunCollision implements Collision<Entity, Entity>{

	private Firearm previous;

	public void onCollision(final Entity player, final Entity magmaGunProp) {

		previous = player.getComponent(ComponentUtils.FIREARM_COMPONENT).getCurrentFirearm();

		player.getComponent(ComponentUtils.FIREARM_COMPONENT).setCurrentFirearm(new MagmaGun());

		magmaGunProp.removeFromWorld();
		
		runOnce(()->{
			player.getComponent(ComponentUtils.FIREARM_COMPONENT).setCurrentFirearm(previous);
		}, Duration.seconds(8));
	}

}