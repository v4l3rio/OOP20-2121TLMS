package components;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import model.Moveable;

public class RandomMovementComponent extends Component implements Moveable {

	private double seconds = 0.0;


	Random rnd = new Random();

	int speed;

	PhysicsComponent physics;

	public RandomMovementComponent(PhysicsComponent physics, int speed) {
		this.physics = physics;
		this.speed = speed;
	}

	@Override
	public void onUpdate(double tpf) {
		

		if (getGameTimer().getNow() > seconds) {

			switch (getRandomDirections()) {
			case LEFT:
				left();
				seconds = seconds + rnd.nextDouble();
				break;

			case RIGHT:
				right();
				seconds = seconds + rnd.nextDouble();
				break;

			case STOP:
				stop();
				seconds = seconds + rnd.nextDouble();
				break;

			default:
				stop();
				seconds = seconds + rnd.nextDouble();
				break;

			}
		}

	}

	public DIRECTIONS getRandomDirections() {
		return DIRECTIONS.values()[rnd.nextInt(DIRECTIONS.values().length)];
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
		this.physics.setVelocityY(0); // incapace di saltare avendo messo 0 come valore
	}
}
