package components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Bullet;

public class BasicBulletComponent extends Component{

	private PhysicsComponent physics;

	private AnimatedTexture texture;

	private AnimationChannel animFire;
	
	private Bullet bullet;
	private double direction;

	public BasicBulletComponent(Bullet bullet, double direction) {
		this.bullet = bullet;
		this.direction = direction;
		animFire = new AnimationChannel(bullet.getMovementTexture(), 3, 200, 120, Duration.seconds(0.30), 0, 2);
		texture = new AnimatedTexture(animFire);
		texture.loop();
	}

	@Override
	public void onAdded() {
		//get the entity to which the component connected, attaching the texture to it
		getEntity().getViewComponent().addChild(texture);
		//reduce bullet size, so to match player's one
		getEntity().setScaleUniform(0.2);
		//set image direction, taken from player
		getEntity().setScaleX(getEntity().getScaleX()*direction);
	}

	@Override
	public void onUpdate(double tpf) {
		if (physics.isMovingX()) {
			if (texture.getAnimationChannel() != animFire) {
				texture.loopAnimationChannel(animFire);
			}
		}
		//set movement direction, equals to player sign
		this.physics.setVelocityX(this.bullet.getShotSpeed()*direction);
	}

}
