package factories;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.Optional;
import java.util.Random;

import com.almasb.fxgl.entity.SpawnData;

import javafx.util.Duration;
import model.Moveable.TYPE_OF_MOVEMENT;
import model.Zombie;
import model.ZombieFemaleDecorator;
import model.ZombieMaleDecorator;
import model.ZombieTextureDecorator;
import model.ZombieTextureDecorator.GENDER;

/**
 * 
 * @author Valerio Di Zio
 * @version 2.3
 * This class creates and spawn zombies with random stats
 */
public class ZombieSpawner extends Thread {

	private static final int INITIAL_SPAWN_Y = -100;
	private static final int INITIAL_SPAWN_X = 500;
	
	private static final int SPAWN_TIME = 5;
	private static final int MINIMUM_LIFE = 10;
	private static final int MINIMUM_SPEED = 170;
	private static final int MINIMUM_DAMAGE = 2;

	private Random rnd;

	public ZombieSpawner() {
		this.rnd = new Random();
	}

	public void run() {
		
		getGameTimer().runAtInterval(() -> {
			
			int x = rnd.nextInt(700) + INITIAL_SPAWN_X;
			
			SpawnData zombieStats = new SpawnData(x, INITIAL_SPAWN_Y);
			
			ZombieTextureDecorator zombieTexturized;
			switch (GENDER.getRandom()) {
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

			
			switch (TYPE_OF_MOVEMENT.getRandom()) {
			case RANDOM:
				spawn("stupidZombie", zombieStats);
				break;
			case FOLLOW:
				spawn("followingZombie", zombieStats);
				break;
			default:
				throw new IllegalArgumentException("Type of movement error!");

			}
			
			
		}, Duration.seconds(SPAWN_TIME));

	}

	private Zombie getRandomZombie() {
		int life = rnd.nextInt(10) + MINIMUM_LIFE;
		int speed = rnd.nextInt(80) + MINIMUM_SPEED;
		int damage = rnd.nextInt(5) + MINIMUM_DAMAGE;
		
		return new Zombie(life, speed, damage);
	}
	

}
