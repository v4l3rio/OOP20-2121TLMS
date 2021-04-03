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

	public BasicBulletComponent(Bullet bullet) {
		this.bullet = bullet;
		animFire = new AnimationChannel(bullet.getMovementTexture(), 3, 200, 120, Duration.seconds(0.30), 0, 2);
		texture = new AnimatedTexture(animFire);
		texture.loop();
	}

	@Override
	public void onAdded() {
		entity.getTransformComponent().setScaleOrigin(new Point2D(0.2, 1));
		entity.getViewComponent().addChild(texture);
		
	}

	@Override
	public void onUpdate(double tpf) {
		if (physics.isMovingX()) {
			if (texture.getAnimationChannel() != animFire) {
				texture.loopAnimationChannel(animFire);
			}
		}
		getEntity().setScaleUniform(-0.2);
		this.physics.setVelocityX(this.bullet.getShotSpeed());
	}

}
