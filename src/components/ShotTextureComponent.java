package components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class ShotTextureComponent extends Component{

	private PhysicsComponent physics;

	private AnimatedTexture texture;

	private AnimationChannel animFire;
	
	private double direction;
	private final static Image SHOTIMAGE = new Image("assets/textures/myShotShrunk.png");
	private final static int NTEXTURES = 3;
	private final static int SHOTSPEED = 500;

	public ShotTextureComponent(double direction) {
		this.direction = direction;
		animFire = new AnimationChannel(SHOTIMAGE, 3, 200, 120, Duration.seconds(0.30), 0, NTEXTURES - 1);
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
		this.physics.setVelocityX(SHOTSPEED*direction);
	}

}
