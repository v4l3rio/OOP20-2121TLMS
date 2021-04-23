package factories;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.almasb.fxgl.dsl.FXGL.spawn;

import java.util.Random;

import com.almasb.fxgl.entity.SpawnData;

import javafx.util.Duration;
import model.Moveable.TypeOfMovement;
import model.Zombie;
import model.ZombieFemaleDecorator;
import model.ZombieMaleDecorator;
import model.ZombieTextureDecorator;
import model.ZombieTextureDecorator.Gender;

/**
 * This class creates and spawn zombies with random stats
 */
public class ZombieSpawner extends Thread {

	private static final int INITIAL_SPAWN_Y = -100;
	private static final int INITIAL_SPAWN_X = 500;
	
	private static final int SPAWN_TIME = 4;
	private static final int MINIMUM_LIFE = 10;
	private static final int MINIMUM_SPEED = 170;
	private static final int MINIMUM_DAMAGE = 2;

	private final Random rnd;

	public ZombieSpawner() {
		this.rnd = new Random();
	}

	/**
	 * Method to start spwning zombies
	 */
	@Override
	public void run() {
		
		getGameTimer().runAtInterval(() -> {
			
			final int x = rnd.nextInt(700) + INITIAL_SPAWN_X;
			
			final SpawnData zombieStats = new SpawnData(x, INITIAL_SPAWN_Y);
			
			ZombieTextureDecorator zombieTexturized;
			
			switch (Gender.getRandom()) {
			case MALE:
				zombieTexturized = new ZombieMaleDecorator(getRandomZombie());
				break;
			case FEMALE:
				zombieTexturized = new ZombieFemaleDecorator(getRandomZombie());
				break;
			default:
				throw new IllegalArgumentException("Gender error!");
			}

			zombieStats.put("zombie", zombieTexturized);
			
			spawn(zombieTexturized.getMovementStrategy(), zombieStats);

			
		}, Duration.seconds(SPAWN_TIME));

	}


	private Zombie getRandomZombie() {
		final int life = rnd.nextInt(10) + MINIMUM_LIFE;
		final int speed = rnd.nextInt(80) + MINIMUM_SPEED;
		final int damage = MINIMUM_DAMAGE;
		
		return new Zombie(life, speed, damage, TypeOfMovement.getRandom());
	}
	

}
