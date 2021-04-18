package components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class ShotMovementComponent extends Component{
	
	private final static int NTEXTURES = 3;
	private final static double SHOT_SCALE = 0.14;	

	private PhysicsComponent physics;

	private AnimatedTexture texture;

	private AnimationChannel animFire;
	
	private double direction;
	private double shotSpeed;

	public ShotMovementComponent(double direction, double shotSpeed, Image shotImage) {
		this.direction = direction;
		animFire = new AnimationChannel(shotImage, 3, (int) (shotImage.getWidth()/NTEXTURES)
				, (int) shotImage.getHeight(), Duration.seconds(0.80), 0, NTEXTURES - 1);
		texture = new AnimatedTexture(animFire);
		this.shotSpeed = shotSpeed;
		texture.loop();
	}

	@Override
	public void onAdded() {
		//get the entity to which the component connected, attaching the texture to it
		getEntity().getViewComponent().addChild(texture);
		//set image direction, taken from player
		getEntity().setScaleX(getEntity().getScaleX()*direction*SHOT_SCALE);
		//reduce bullet size, so to match player's one
		getEntity().setScaleY(getEntity().getScaleY()*SHOT_SCALE);
	}

	@Override
	public void onUpdate(double tpf) {
		if (physics.isMovingX()) {
			if (texture.getAnimationChannel() != animFire) {
				texture.loopAnimationChannel(animFire);
			}
		}
		//set movement direction, equals to player sign
		this.physics.setVelocityX(this.shotSpeed*direction);
	}

}
