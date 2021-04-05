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
	private Point2D playerPos;

	public BasicBulletComponent(Bullet bullet, Point2D playerPos, double direction) {
		this.bullet = bullet;
		this.playerPos = playerPos;
		this.direction = direction;
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
		//set image direction, taken from player
		getEntity().setScaleUniform(0.2);
		//bullet image is facing the opposite of player, hence the '-'
		getEntity().setScaleX(getEntity().getScaleX()*-direction);
		//set movement direction, equals to player sign
		this.physics.setVelocityX(this.bullet.getShotSpeed()*direction);
	}

}
