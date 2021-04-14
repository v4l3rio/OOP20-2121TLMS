package factories;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.Random;

import com.almasb.fxgl.entity.SpawnData;

import javafx.util.Duration;

import model.Zombie;
import model.ZombieFemaleDecorator;
import model.ZombieMaleDecorator;
import model.ZombieTextureDecorator;

public class ZombieSpawner extends Thread {

	private static final int BASEY = -100;
	
	private static final int SPAWNTIME = 5;
	private static final int BASELIFE = 5;
	private static final int BASESPEED = 150;
	private static final int BASEX = 500;
	private static final int BASEDAMAGE = 2;

	private Random rnd;
	SpawnData zombieStats;
	ZombieTextureDecorator zombieTexturized;
	int life, speed, damage, x;

	private static enum GENDER {
		MALE, FEMALE
	}

	public ZombieSpawner() {
		this.rnd = new Random();
	}

	public void run() {
		getGameTimer().runAtInterval(() -> {
			setRandomStats();
			zombieStats = new SpawnData(x, BASEY);

			switch (getRandomGender()) {
			case MALE:
				zombieTexturized = new ZombieMaleDecorator(new Zombie(life, speed, damage));
				break;
			case FEMALE:
				zombieTexturized = new ZombieFemaleDecorator(new Zombie(life, speed, damage));
				break;

			}

			zombieStats.put("Life", zombieTexturized.getLife());
			zombieStats.put("Damage", zombieTexturized.getDamage());
			zombieStats.put("Speed", zombieTexturized.getSpeed());
			zombieStats.put("Texture", zombieTexturized.getTexture().getTextureMap());

			spawn("zombie", zombieStats);
		}, Duration.seconds(SPAWNTIME));

	}

	public void setRandomStats() {
		life = rnd.nextInt(5) + BASELIFE;
		speed = rnd.nextInt(40) + BASESPEED;
		damage = rnd.nextInt(5) + BASEDAMAGE;
		x = rnd.nextInt(700) + BASEX;
	}

	public GENDER getRandomGender() {
		return GENDER.values()[rnd.nextInt(GENDER.values().length)];
	}

}
