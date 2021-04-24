package components;

import java.util.Map;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;
import javafx.util.Duration;
import model.TLMSType;

/**
 * 
 * this class is used to create, set and manage the player's texture
 */
public class TextureComponent extends Component{
	
	public static final double TIME_ANIM_IDLE = 1;
	public static final double TIME_ANIM_RUN = 1;
	public static final double TIME_ANIM_JUMP = 1.7;
	public static final double TIME_ANIM_DEATH = 1.7;
	public static final double TIME_ANIM_DAMAGE = 0.8;
	
	private static final int X_DIM = 48;
	private static final int Y_DIM = 48;

	
	private AnimatedTexture animTexture;
    private AnimationChannel animIdle, animRun, animJump, animDeath, animDamage;
    private PhysicsComponent physics;
    
    /**
     * @param a map of textures
     */
    public TextureComponent (final Map<TLMSType, String> textures) {   	
    	this.animIdle = new AnimationChannel(FXGL.image(textures.get(TLMSType.IDLE)), 5, X_DIM, Y_DIM, Duration.seconds(TIME_ANIM_IDLE), 0, 4);
    	this.animRun = new AnimationChannel(FXGL.image(textures.get(TLMSType.RUN)), 6, X_DIM, Y_DIM, Duration.seconds(TIME_ANIM_RUN), 0, 5);
    	this.animJump = new AnimationChannel(FXGL.image(textures.get(TLMSType.JUMP)), 2, X_DIM, Y_DIM, Duration.seconds(TIME_ANIM_JUMP), 1, 1);
    	this.animDeath = new AnimationChannel(FXGL.image(textures.get(TLMSType.DEAD)), 8, X_DIM, Y_DIM, Duration.seconds(TIME_ANIM_DEATH), 0, 7);
    	this.animDamage = new AnimationChannel(FXGL.image(textures.get(TLMSType.DEAD)), 4, X_DIM, Y_DIM, Duration.seconds(TIME_ANIM_DAMAGE), 0, 3);
  	
    	animTexture = new AnimatedTexture(animIdle);
    }
    
    @Override
    public void onAdded() { //appena entità viene aggiunto al mondo, viene eseguito
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(animTexture);
        getEntity().setScaleUniform(entity.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getDimension()); //setta la grandezza iniziale in percentuale
    }
    
    @Override
    public void onUpdate(final double tpf) { 
    	if(entity.getComponent(ComponentUtils.PLAYER_COMPONENT).isDead()) {
    		if (animTexture.getAnimationChannel() != animDeath) {
    			animTexture.loopAnimationChannel(animDeath); 
    		}    			
    	} else if (entity.getComponent(ComponentUtils.PLAYER_COMPONENT).isAttacked()) {
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
    	if(!entity.getComponent(ComponentUtils.PLAYER_COMPONENT).isDead()) {
    		entity.getViewComponent().removeChild(animTexture);
    	}
    }
}
