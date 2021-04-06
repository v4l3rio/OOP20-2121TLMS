package controller;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import collisions.Collision;
import collisions.PlayerZombieCollision;
import collisions.BulletZombieCollision;

import view.Level;
import view.ZombieLevel;
import static com.almasb.fxgl.dsl.FXGL.*;

public class TLMSApp extends GameApplication {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 640;
	private TLMSFactory factory;
	private Level zombieLevel;

	private final Collision<Entity, Entity> bulletColZombie = new BulletZombieCollision();
	private final Collision<Entity, Entity> playerColZombie = new PlayerZombieCollision();

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(WIDTH);
		settings.setHeight(HEIGHT);

		settings.setApplicationMode(ApplicationMode.DEVELOPER);
	}

	@Override
	public void initGame() {

		zombieLevel = new ZombieLevel();
		factory = new TLMSFactory();
		getGameWorld().addEntityFactory(factory);

		setLevelFromMap(zombieLevel.getLevel());
		// for(int i = 0; i< 5;i++)
		spawn("zombie", 100, 50);


	}


	@Override
	protected void initPhysics() {
		getPhysicsWorld().addCollisionHandler(new CollisionHandler(TLMSType.BULLET, TLMSType.ZOMBIE) {
			@Override
			protected void onCollisionBegin(final Entity bullet, final Entity zombie) {
				try {
					System.out.println("Collisione Avvenuta");
					bulletColZombie.onCollision(bullet, zombie);
				} catch (Exception e) {
					System.out.println("Collisions Bullet - Zombie, Not Work!");
				}
			}
		});

		getPhysicsWorld().addCollisionHandler(new CollisionHandler(TLMSType.PLAYER, TLMSType.ZOMBIE) {
			@Override
			protected void onCollisionBegin(final Entity player, final Entity zombie) {
				try {
					System.out.println("Collisione Avvenuta");
					playerColZombie.onCollision(player, zombie);
				} catch (Exception e) {
					System.out.println("Collisions Player - Zombie, Not Work!");
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
