package model;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent extends Component {
		
	//whithout physics
    //private int speed = 0;
    
    private PhysicsComponent physics;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;
    
    private int jumps = 1;

    public AnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image("JTRunning.png"), 8, 112, 134, Duration.seconds(1), 5, 5);
        animWalk = new AnimationChannel(FXGL.image("JTRunning.png"), 8, 112, 134, Duration.seconds(1), 0, 7);

        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
        
        physics.onGroundProperty().addListener((obs, old, isOnGround) -> {
            if (isOnGround) {

                jumps = 1; 
            }
        });
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
    	
    	getEntity().setScaleX(1); //direzione personaggio
        physics.setVelocityX(200);
        
        //without velocity
//        speed = 150;
//
//        getEntity().setScaleX(1);
    }

    public void moveLeft() {
    	
    	getEntity().setScaleX(-1); //direzione personaggio
        physics.setVelocityX(-200);
        
        //without physics
//        speed = -150;
//
//        getEntity().setScaleX(-1);
    }
    
    public void stop() {
        physics.setVelocityX(0);
    }
    
    public void jump() {
        if (jumps == 0) {
            return;
        }

        physics.setVelocityY(-450);

        jumps--;
    }
}