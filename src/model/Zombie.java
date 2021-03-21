package model;

import static com.almasb.fxgl.dsl.FXGL.image;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Zombie extends Component{
	
	  private PhysicsComponent physics;

	    private AnimatedTexture texture;

	    private AnimationChannel animIdle, animWalk;
	    
	    public Zombie() {
	    	
	    	
	    	 Image imageIdle = image("zombie_idle.png");
	    	 Image imageWolk = image("zombie_move.png");
	    	 animIdle = new AnimationChannel(imageIdle, 4, 85, 120, Duration.seconds(0.66), 0, 3);
	    	 animWalk = new AnimationChannel(imageWolk, 6, 70, 120, Duration.seconds(0.66), 0, 3);
	    	 
	    	 texture = new AnimatedTexture(animIdle);
	         texture.loop();
	    	
	    }
	    
	    @Override
	    public void onAdded() {
	        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
	        entity.getViewComponent().addChild(texture);

			/*
			 * physics.onGroundProperty().addListener((obs, old, isOnGround) -> { if
			 * (isOnGround) { //play("land.wav"); jumps = 2; //numero massimo di salti } });
			 */
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
	    
	    public void left() {
	        getEntity().setScaleX(-1);
	        physics.setVelocityX(-170);
	    }

	    public void right() {
	        getEntity().setScaleX(1);
	        physics.setVelocityX(170);
	    }

	    public void stop() {
	        physics.setVelocityX(0);
	    }


}
