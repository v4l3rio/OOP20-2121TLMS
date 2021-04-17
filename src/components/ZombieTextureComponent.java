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
 * 
 * @version 2.3
 * This component attached to a zombie enriches it with textures and animations
 */

public class ZombieTextureComponent extends Component {

	private PhysicsComponent physics;

	private AnimatedTexture texture;

	private AnimationChannel animIdle, animWalk, animAttack, animDead;

	private boolean attacking = false;

	Map<TLMSType, String> textureMap;

	/**
	 * 
	 * @param textureMap, texture map with all zombie states
	 */
	public ZombieTextureComponent(Map<TLMSType, String> textureMap) {
		this.textureMap = textureMap;
		animIdle = new AnimationChannel(new Image(this.textureMap.get(TLMSType.IDLE)), 15, 132, 160,
				Duration.seconds(0.66), 0, 14);
		animWalk = new AnimationChannel(new Image(this.textureMap.get(TLMSType.WALK)), 10, 132, 160,
				Duration.seconds(0.66), 0, 9);
		animAttack = new AnimationChannel(new Image(this.textureMap.get(TLMSType.ATTACK)), 8, 132, 160,
				Duration.seconds(0.66), 0, 7);
		animDead = new AnimationChannel(new Image(this.textureMap.get(TLMSType.DEAD)), 12, 192, 160,
				Duration.seconds(0.66), 0, 11);
		texture = new AnimatedTexture(animIdle);
		texture.loop();
	}

	@Override
	public void onAdded() {
		entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
		entity.getViewComponent().addChild(texture);
		entity.setScaleUniform(1);
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	private boolean isAttacking() {
		return this.attacking;
	}

	@Override
	public void onUpdate(double tpf) {

		if (entity.getComponent(ComponentUtils.HEALTH_COMPONENT).isZero()) {
			if (texture.getAnimationChannel() != animDead) {
				texture.playAnimationChannel(animDead);
				FXGL.getGameTimer().runOnceAfter(() -> {
					entity.removeFromWorld();
				}, Duration.seconds(0.66));
			}
		}

		else if (isAttacking()) {
			if (texture.getAnimationChannel() != animAttack) {
				texture.playAnimationChannel(animAttack);
				FXGL.getGameTimer().runOnceAfter(() -> {
					setAttacking(false);
				}, Duration.seconds(0.66));
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
