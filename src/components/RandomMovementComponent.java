package components;

import java.util.Random;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class RandomMovementComponent extends Component {
	private double levelTime = 0.0;
	private int seconds = 0;
	private int nextDirection = 0;
	Random rnd = new Random();
	PhysicsComponent physics;

	public RandomMovementComponent(PhysicsComponent physics) {
		this.physics = physics;
	}

	@Override
	public void onUpdate(double tpf) {
		levelTime = levelTime + tpf;
		nextDirection = rnd.nextInt(3);
		
		if (levelTime > seconds) {
			
			switch (nextDirection) {
			case 0:

				left();
				seconds++;

				break;
			case 1:

				right();
				seconds++;

				break;
			case 2:

				stop();
				seconds++;

				break;

			default:

				stop();
				seconds++;

				break;

			}
		}

	}

	public void left() {
		getEntity().setScaleX(-1);

		this.physics.setVelocityX(-170);
	}

	public void right() {
		getEntity().setScaleX(1);
		this.physics.setVelocityX(170);
	}

	public void stop() {
		this.physics.setVelocityX(0);
	}
}
