package components;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import java.util.Random;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import model.Moveable;

public class FollowPlayerComponent extends Component implements Moveable{
	
	Entity player;
	PhysicsComponent physics;
	int speed;
	
	private double seconds = 0.0;


	Random rnd = new Random();

	
	public FollowPlayerComponent(Entity player, PhysicsComponent physics, int speed) {
		this.player = player;
		this.physics = physics;
		this.speed = speed;
	}
	
	@Override
	public void onUpdate(double tpf) {
		
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
		getEntity().setScaleX(-1);
		this.physics.setVelocityX(-(this.speed));
	}

	public void right() {
		getEntity().setScaleX(1);
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
