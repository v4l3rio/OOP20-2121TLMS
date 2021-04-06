package components;

import java.util.Map;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.TLMSType;

public class ZombieTextureComponent extends Component {

	private PhysicsComponent physics;

	private AnimatedTexture texture;

	private AnimationChannel animIdle, animWalk, animAttack, animDead;

	
	private double i = 0, j= 0;
	private boolean isDead = false;
	private boolean isAttack= false;

	Map<TLMSType, String> textureMap;

	public ZombieTextureComponent(Map<TLMSType, String> textureMap) {
		this.textureMap = textureMap;
		animIdle = new AnimationChannel(new Image(this.textureMap.get(TLMSType.IDLE)), 15, 430, 519,
				Duration.seconds(0.66), 0, 14);
		animWalk = new AnimationChannel(new Image(this.textureMap.get(TLMSType.WALK)), 10, 430, 519,
				Duration.seconds(0.66), 0, 9);
		animAttack = new AnimationChannel(new Image(this.textureMap.get(TLMSType.ATTACK)), 8, 430, 519,
				Duration.seconds(0.66), 0, 7);
		animDead = new AnimationChannel(new Image(this.textureMap.get(TLMSType.DEAD)), 12, 629, 526,
				Duration.seconds(0.66), 0, 11);
		texture = new AnimatedTexture(animIdle);
		texture.loop();
	}

	@Override
	public void onAdded() {
		entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
		entity.getViewComponent().addChild(texture);
		entity.setScaleUniform(0.2);
	}
	
	public void setAttack(boolean isAttack) {
		this.isAttack = isAttack;
	}

	@Override
	public void onUpdate(double tpf) {
		
		if(isDead) {
			i = i + tpf;
			if(i>1) {
				entity.removeFromWorld();
			}
		}
		
		if(isAttack) {
			j = j + tpf;
			if(j>0.6) {
				this.isAttack= false;
				j = 0;
			}
		}

		if(entity.getComponent(ComponentUtils.HEALTH_COMPONENT).isZero()){
			if (texture.getAnimationChannel() != animDead) {
				texture.playAnimationChannel(animDead);
				isDead = true;
				
			}
		}
		else if(this.isAttack) {
			if (texture.getAnimationChannel() != animAttack) {
				texture.playAnimationChannel(animAttack);
				System.out.println("Attacco!");
			}
		}
		
		else if (physics.isMovingX()) {
			if (texture.getAnimationChannel() != animWalk) {
				texture.loopAnimationChannel(animWalk);
			}
		} 
		else {
			if (texture.getAnimationChannel() != animIdle) {
				texture.loopAnimationChannel(animIdle);
			}
		}

	}

}
