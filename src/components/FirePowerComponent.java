package components;

import com.almasb.fxgl.dsl.FXGL;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.util.Duration;


public class FirePowerComponent extends Component{
	
	/**
	 * this keeps track of the current textures
	 * note: I could have declared it as "final" 
	 * but I chose to leave it more open for a hypothetical future extension
	 */
	private AnimationChannel animFire;
	private AnimatedTexture animTexture;
	
	public FirePowerComponent() {
		this.animFire = new AnimationChannel(FXGL.image("FirePower.png"), 4, 141, 138, Duration.seconds(1), 0, 3);
		animTexture = new AnimatedTexture(animFire);
	}
	
	@Override
    public void onAdded() { //appena entità viene aggiunto al mondo, viene eseguito
        entity.getViewComponent().addChild(animTexture);
        getEntity().setScaleUniform(0.3);
	}
	
	 @Override
	 public void onUpdate(final double tpf) { 	 
		 if (animTexture.getAnimationChannel() != animFire) {
             animTexture.loopAnimationChannel(animFire);
         }
	 }
}
