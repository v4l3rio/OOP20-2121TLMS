package components;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import model.Moveable;

/**
 * This component attached to an entity makes it move randomly
 */
public class RandomMovementComponent extends Component implements Moveable {

	private double seconds;

	private final Random rnd = new Random();

	private final int speed;

	private final PhysicsComponent physics;

	/**
	 * 
	 * @param physics - PhysicsComponent
	 * @param speed - movement speed
	 */
	public RandomMovementComponent(final PhysicsComponent physics, final int speed) {
		this.physics = physics;
		this.speed = speed;
		this.seconds = 0.0;
	}

	@Override
	public void onUpdate(final double tpf) {
		

		if (getGameTimer().getNow() > seconds) {

			switch (Directions.getRandom()) {
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
