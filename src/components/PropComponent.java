package components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;
/**
 * Models a prop component, to be attached to an entity.
 * Its role is to create a game object with its image and preset scale,
 * which follows physics.
 */
public class PropComponent extends Component {
	
	private final static int PROPDEFAULTNTEXTURES = 3;
	private final static double PROPDEFAULTSCALE = 0.3;
	private final static double PROPDEFAULTCHANNELDURATION = 0.3;

	private AnimatedTexture propTexture;
	private AnimationChannel propChannel;
	
	/**
	 * 
	 * @param propIMG is the image containing prop's textures to set animation
	 */
	public PropComponent(Image propIMG) {
		propChannel = new AnimationChannel(propIMG, 3, (int) (propIMG.getWidth()/PROPDEFAULTNTEXTURES)
				, (int) propIMG.getHeight(), Duration.seconds(PROPDEFAULTCHANNELDURATION), 0, PROPDEFAULTNTEXTURES - 1);
		propTexture = new AnimatedTexture(propChannel);
		propTexture.loop();
	}

	/**
	 * adds texture to the entity, setting due scale
	 */
	public void onAdded() {
		getEntity().getViewComponent().addChild(propTexture);
		getEntity().setScaleUniform(PROPDEFAULTSCALE);
	}
}
