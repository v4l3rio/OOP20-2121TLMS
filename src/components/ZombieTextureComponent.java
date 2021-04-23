package components;

import java.util.Map;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.TLMSType;

/**
 * This component attached to a zombie enriches it with textures and animations
 */

public class ZombieTextureComponent extends Component {
	
	private static final double ANIMATION_TIME = 0.66;

	private final PhysicsComponent physics;

	private final AnimatedTexture texture;

	private final AnimationChannel animIdle, animWalk, animAttack, animDead;

	private boolean attacking;


	/**
	 * @param textureMap - texture map with all zombie states
	 */
	public ZombieTextureComponent(final Map<TLMSType, String> textureMap, final PhysicsComponent physics) {
		this.physics = physics;
		animIdle = new AnimationChannel(new Image(textureMap.get(TLMSType.IDLE)), 15, 132, 160,
				Duration.seconds(ANIMATION_TIME), 0, 14);
		animWalk = new AnimationChannel(new Image(textureMap.get(TLMSType.WALK)), 10, 132, 160,
				Duration.seconds(ANIMATION_TIME), 0, 9);
		animAttack = new AnimationChannel(new Image(textureMap.get(TLMSType.ATTACK)), 8, 132, 160,
				Duration.seconds(ANIMATION_TIME), 0, 7);
		animDead = new AnimationChannel(new Image(textureMap.get(TLMSType.DEAD)), 12, 192, 160,
				Duration.seconds(ANIMATION_TIME), 0, 11);
		texture = new AnimatedTexture(animIdle);
		texture.loop();
		this.attacking = false;
	}

	@Override
	public void onAdded() {
		entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
		entity.getViewComponent().addChild(texture);
		entity.setScaleUniform(0.8);
	}

	public void setAttacking(final boolean attacking) {
		this.attacking = attacking;
	}

	private boolean isAttacking() {
		return this.attacking;
	}

	@Override
	public void onUpdate(final double tpf) {

		if (entity.getComponent(ComponentUtils.HEALTH_COMPONENT).isZero()) {
			if (texture.getAnimationChannel() != animDead) {
				texture.playAnimationChannel(animDead);
				FXGL.getGameTimer().runOnceAfter(() -> {
					entity.removeFromWorld();
				}, Duration.seconds(ANIMATION_TIME));
			}
		}

		else if (isAttacking()) {
			if (texture.getAnimationChannel() != animAttack) {
				texture.playAnimationChannel(animAttack);
				FXGL.getGameTimer().runOnceAfter(() -> {
					setAttacking(false);
				}, Duration.seconds(ANIMATION_TIME));
			}
		}

		else if (physics.isMovingX()) {
			if (texture.getAnimationChannel() != animWalk) {
				texture.loopAnimationChannel(animWalk);
			}
		} else {
			if (texture.getAnimationChannel() != animIdle) {
				texture.loopAnimationChannel(animIdle);
			}
		}

	}

}
