package components;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

import java.util.Random;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import model.Moveable;
/**
 * 
 * @author Valerio Di Zio
 * @version 1.1
 * This component gives the ability to move a zombie randomly or following the player
 *
 */
public class ZombieMovementComponent_TODELETE extends Component implements Moveable {

	private Entity player;
	private PhysicsComponent physics;
	private int speed;

	private double seconds = 0.0;

	private TYPE_OF_MOVEMENT selectedType;

	private Random rnd = new Random();

	public ZombieMovementComponent_TODELETE(Entity player, PhysicsComponent physics, int speed) {
		this.player = player;
		this.physics = physics;
		this.speed = speed;
	}

	@Override
	public void onAdded() {
		/**
		 * define the type of movement randomly
		 */
		selectedType = getRandomTypeOfMovement();
	}

	@Override
	public void onUpdate(double tpf) {
		if (getGameTimer().getNow() > seconds)

			if (selectedType == TYPE_OF_MOVEMENT.RANDOM) {

				switch (getRandomDirections()) {
				case LEFT:
					left();
					break;

				case RIGHT:
					right();
					break;

				case STOP:
					stop();
					break;

				default:
					stop();
					break;

				}
			} else if (selectedType == TYPE_OF_MOVEMENT.FOLLOW) {

				if (entity.getX() > this.player.getX()) {
					left();
				} else {
					right();
				}
			}

	}

	/**
	 * 
	 * @return random direction
	 */
	private DIRECTIONS getRandomDirections() {
		return DIRECTIONS.values()[rnd.nextInt(DIRECTIONS.values().length)];
	}
	
	/**
	 * 
	 * @return random type of zombie, follow the player or random movement
	 */
	private TYPE_OF_MOVEMENT getRandomTypeOfMovement() {
		return TYPE_OF_MOVEMENT.values()[rnd.nextInt(TYPE_OF_MOVEMENT.values().length)];
	}

	public void left() {
		getEntity().setScaleX(-1);
		this.physics.setVelocityX(-(this.speed));
		seconds = seconds + rnd.nextDouble();
	}

	public void right() {
		getEntity().setScaleX(1);
		this.physics.setVelocityX(this.speed);
		seconds = seconds + rnd.nextDouble();
	}

	public void stop() {
		this.physics.setVelocityX(0);
		seconds = seconds + rnd.nextDouble();
	}

	@Override
	public void jump() {
		this.physics.setVelocityY(0); // incapace di saltare avendo messo 0 come valore
		seconds = seconds + rnd.nextDouble();
	}
}
