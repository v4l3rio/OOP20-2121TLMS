package components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import model.Player;
import model.PlayerTexture;

public class AnimationComponent extends Component {
		
	//whithout physics
    //private int speed = 0;
    
    private PhysicsComponent physics;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animRun, animJump, animDeath, animDamage;
    
    private Player player;
    private PlayerTexture playerTexture;
    
    private boolean isAttacked = false;
    private boolean isDead = false;

    public AnimationComponent() {
    	this.player = new Player();
    	this.playerTexture = new PlayerTexture();
    	
        animIdle = new AnimationChannel(playerTexture.getImageIdle(), 5, 48, 48, Duration.seconds(1), 0, 4);
        animRun = new AnimationChannel(playerTexture.getImageRun(), 6, 48, 48, Duration.seconds(1), 0, 5);
        animJump = new AnimationChannel(playerTexture.getImageJump(), 2, 48, 48, Duration.seconds(1.7), 0, 1);
        animDeath = new AnimationChannel(playerTexture.getImageDeath(), 8, 48, 48, Duration.seconds(1.7), 0, 7);
        animDamage = new AnimationChannel(playerTexture.getImageDeath(), 8, 48, 48, Duration.seconds(0.8), 0, 3);
        
        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() { //appena entità viene aggiunto al mondo, viene eseguito
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
        getEntity().setScaleUniform(1.5); //setta la grandezza iniziale in percentuale
        
        physics.onGroundProperty().addListener((obs, old, isOnGround) -> { //obs(observer) //old??
        	
            if (isOnGround) {
            	player.resetNJumps();
            }
        });
    }

    @Override
    public void onUpdate(double tpf) {   	//mentre è nel mondo fa questo
    	if(this.isDead) {
    		if (texture.getAnimationChannel() != animDeath) {
    			texture.loopAnimationChannel(animDeath); 
    		}    			
    	} else if (this.isAttacked) {
    		if(texture.getAnimationChannel() != animDamage) {
    			texture.loopAnimationChannel(animDamage); 
    		}
    	} else {
	    	if (physics.isMovingX() && physics.isOnGround()) {
	            if (texture.getAnimationChannel() != animRun) {
	                texture.loopAnimationChannel(animRun);
	            }
	        } else if(physics.isMovingY()){
	        	if (texture.getAnimationChannel() != animJump) {
	        		texture.loopAnimationChannel(animJump);
	        	}
	        } else {
		            if (texture.getAnimationChannel() != animIdle) {
		                texture.loopAnimationChannel(animIdle);
		            }
	        }
    	}
    	
    	//without physics
//        entity.translateX(speed * tpf);
//
//        if (speed != 0) {
//
//            if (texture.getAnimationChannel() == animIdle) {
//                texture.loopAnimationChannel(animWalk);
//            }
//
//            speed = (int) (speed * 0.9);
//
//            if (FXGLMath.abs(speed) < 1) {
//                speed = 0;
//                texture.loopAnimationChannel(animIdle);
//            }
//        }
    }

    public void moveRight() {
    	
    	getEntity().setScaleX(1.5); //direzione personaggio
        physics.setVelocityX(player.getSpeed());
        
        //without velocity
//        speed = 150;
//
//        getEntity().setScaleX(1);
    }

    public void moveLeft() {

    	getEntity().setScaleX(-1.5); //direzione personaggio
        physics.setVelocityX(-player.getSpeed());
        
        //without physics
//        speed = -150;
//
//        getEntity().setScaleX(-1);
    }
    
    public void stop() {
        physics.setVelocityX(0);
    }
    
    public void jump() {
        if (player.getNJumps() > 0) {
	        physics.setVelocityY(player.getJumpHeight());
	        player.decreaseJumps();
        }
    }
    
    public void attacked() {
    	this.isAttacked = true;
    	
    	if(entity.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
    		this.dead();
    	}    	
    	
    	getGameTimer().runOnceAfter(() -> {
    	    this.isAttacked = false;
    	}, Duration.seconds(0.8));
    	
    }
    
    public boolean isDead() {
    	return this.isDead;
    }
    
    public void dead() {
    	this.isDead = true;
    }
    
//    public void transformation() {
//    	player.toRed();
//    }
}