package components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.scene.image.Image;
import javafx.util.Duration;
/**
 * Implements a component managing shot movement, both graphically 
 * and logically (also used to handle collisions)
 */
public class ShotMovementComponent extends Component{
	
	//Preset values, we want shot size to be SHOT_SCALE big, using NTEXTURES for its animation
	private final static int NTEXTURES = 3;
	private final static double SHOT_SCALE = 0.14;	

	private final PhysicsComponent physics;

	private final AnimatedTexture texture;

	private final AnimationChannel animShot;
	
	private final double direction;
	private final double shotSpeed;
	/**
	 * Constructor with necessary fields
	 * @param direction where the shot movement is directed
	 * @param shotSpeed shot speed
	 * @param shotImage the Image contatining NTEXTURES, used for animation
	 */
	public ShotMovementComponent(final PhysicsComponent physics, final double direction, final double shotSpeed, final Image shotImage) {
		this.physics = physics;
		this.direction = direction;
		animShot = new AnimationChannel(shotImage, 3, (int) (shotImage.getWidth()/NTEXTURES)
				, (int) shotImage.getHeight(), Duration.seconds(0.80), 0, NTEXTURES - 1);
		texture = new AnimatedTexture(animShot);
		this.shotSpeed = shotSpeed;
		texture.loop();
	}

	@Override
	public void onAdded() {
		//get the entity to which the component connected, attaching the texture to it
		getEntity().getViewComponent().addChild(texture);
		//set image direction, taken from player
		getEntity().setScaleX(getEntity().getScaleX()*direction*SHOT_SCALE);
		//reduce shot size, so to match player's one
		getEntity().setScaleY(getEntity().getScaleY()*SHOT_SCALE);
	}

	@Override
	public void onUpdate(final double tpf) {
		if (physics.isMovingX() && texture.getAnimationChannel() != animShot) {
			texture.loopAnimationChannel(animShot);
		}
		//set movement direction, equals to player sign
		this.physics.setVelocityX(this.shotSpeed*direction);
	}

}
