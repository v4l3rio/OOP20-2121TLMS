package components;

import java.util.Map;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import controller.TLMSType;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class ZombieTextureComponent extends Component{
	
	private PhysicsComponent physics;

	private AnimatedTexture texture;

	private AnimationChannel animIdle, animWalk;
	
	Map<TLMSType, String> textureMap;

	public ZombieTextureComponent(Map<TLMSType, String> textureMap) {
		this.textureMap = textureMap;
		animIdle = new AnimationChannel(new Image(this.textureMap.get(TLMSType.IDLE)), 4, 85, 120, Duration.seconds(0.66), 0, 3);
		animWalk = new AnimationChannel(new Image(this.textureMap.get(TLMSType.WALK)), 6, 70, 120, Duration.seconds(0.66), 0, 3);
		texture = new AnimatedTexture(animIdle);
		texture.loop();
	}
	
	@Override
	public void onAdded() {
		entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
		entity.getViewComponent().addChild(texture);
		
		
	}

	@Override
	public void onUpdate(double tpf) {
		if (physics.isMovingX()) {
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
