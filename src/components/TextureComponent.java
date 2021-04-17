package components;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;
import javafx.util.Duration;
import model.TLMSType;

public class TextureComponent extends Component{
	
	private AnimatedTexture animTexture;
    private AnimationChannel animIdle, animRun, animJump, animDeath, animDamage;
    private PhysicsComponent physics;
    
    public TextureComponent (Map<TLMSType, String> textures) {   	
    	this.animIdle = new AnimationChannel(image(textures.get(TLMSType.IDLE)), 5, 48, 48, Duration.seconds(1), 0, 4);
    	this.animRun = new AnimationChannel(image(textures.get(TLMSType.RUN)), 6, 48, 48, Duration.seconds(1), 0, 5);
    	this.animJump = new AnimationChannel(image(textures.get(TLMSType.JUMP)), 2, 48, 48, Duration.seconds(1.7), 1, 1);
    	this.animDeath = new AnimationChannel(image(textures.get(TLMSType.DEAD)), 8, 48, 48, Duration.seconds(1.7), 0, 7);
    	this.animDamage = new AnimationChannel(image(textures.get(TLMSType.DEAD)), 4, 48, 48, Duration.seconds(0.8), 0, 3);
  	
    	animTexture = new AnimatedTexture(animIdle);
    }
    
    @Override
    public void onAdded() { //appena entità viene aggiunto al mondo, viene eseguito
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(animTexture);
        getEntity().setScaleUniform(entity.getComponent(PlayerComponent.class).getPlayer().getDimension()); //setta la grandezza iniziale in percentuale
    }
    
    @Override
    public void onUpdate(double tpf) { 
    	if(entity.getComponent(PlayerComponent.class).isDead()) {
    		if (animTexture.getAnimationChannel() != animDeath) {
    			animTexture.loopAnimationChannel(animDeath); 
    		}    			
    	} else if (entity.getComponent(PlayerComponent.class).isAttacked()) {
    		if(animTexture.getAnimationChannel() != animDamage) {
    			animTexture.loopAnimationChannel(animDamage); 
    		}
    	} else {
	    	if (physics.isMovingX() && physics.isOnGround()) {
	            if (animTexture.getAnimationChannel() != animRun) {
	                animTexture.loopAnimationChannel(animRun);
	            }
	        } else if(physics.isMovingY()){
	        	if (animTexture.getAnimationChannel() != animJump) {
	        		animTexture.loopAnimationChannel(animJump);
	        	}
	        } else {
		            if (animTexture.getAnimationChannel() != animIdle) {
		                animTexture.loopAnimationChannel(animIdle);
		            }
	        }
    	}
    }
    
    @Override
    public void onRemoved() {
    	if(!entity.getComponent(PlayerComponent.class).isDead()) {
    		entity.getViewComponent().removeChild(animTexture);
    	}
    }

}
