package components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class PropComponent extends Component {
	
	private final static int NTEXTURES = 3;

	private AnimatedTexture propTexture;

	private AnimationChannel propChannel;
	
	public PropComponent(Image propIMG) {
		propChannel = new AnimationChannel(propIMG, 3, (int) (propIMG.getWidth()/NTEXTURES)
				, (int) propIMG.getHeight(), Duration.seconds(0.30), 0, NTEXTURES - 1);
		propTexture = new AnimatedTexture(propChannel);
		propTexture.loop();
	}

	public void onAdded() {
		getEntity().getViewComponent().addChild(propTexture);
		getEntity().setScaleUniform(0.3);
	}
}
