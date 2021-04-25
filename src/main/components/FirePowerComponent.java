package main.components;

import com.almasb.fxgl.dsl.FXGL;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.util.Duration;


public class FirePowerComponent extends Component{
	
	private static final int X_DIM = 42;
	private static final int Y_DIM = 41;
	
	/**
	 * this keeps track of the current textures
	 * note: I could have declared it as "final" 
	 * but I chose to leave it more open for a hypothetical future extension
	 */
	private final AnimationChannel animFire;
	private final AnimatedTexture animTexture;
	
	public FirePowerComponent() {
		this.animFire = new AnimationChannel(FXGL.image("FirePower.png"), 4, X_DIM, Y_DIM, Duration.seconds(1), 0, 3);
		animTexture = new AnimatedTexture(animFire);
	}
	
	@Override
    public void onAdded() {
        entity.getViewComponent().addChild(animTexture);
        getEntity().setScaleUniform(1);
	}
	
	 @Override
	 public void onUpdate(final double tpf) { 	 
		 if (animTexture.getAnimationChannel() != animFire) {
             animTexture.loopAnimationChannel(animFire);
         }
	 }
}
