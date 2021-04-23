package components;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import java.util.Random;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import model.Moveable;

/**
 * This component attached to an entity will make it follow the player
 */
public class FollowPlayerComponent extends Component implements Moveable{
	
	private final Entity player;
	private final PhysicsComponent physics;
	private final int speed;
	
	private double seconds;


	private final Random rnd = new Random();

	/**
	 * 
	 * @param player - player to follow
	 * @param physics - PhysicsComponent
	 * @param speed - movement speed
	 */
	public FollowPlayerComponent(final Entity player, final PhysicsComponent physics, final int speed) {
		this.player = player;
		this.physics = physics;
		this.speed = speed;
		this.seconds = 0.0;
	}
	
	@Override
	public void onUpdate(final double tpf) {
		
		if (getGameTimer().getNow() > seconds) {
			
			if(entity.getX()>this.player.getX()) {
				left();
				seconds = seconds + rnd.nextDouble();
			}
			else {
				right();
				seconds = seconds + rnd.nextDouble();
			}
		}
	}
	
	public void left() {
		getEntity().setScaleX(-0.8);
		this.physics.setVelocityX(-(this.speed));
	}

	public void right() {
		getEntity().setScaleX(0.8);
		this.physics.setVelocityX(this.speed);
	}

	public void stop() {
		this.physics.setVelocityX(0);
	}

	@Override
	public void jump() {
		this.physics.setVelocityY(-500); 
	}
	

}
