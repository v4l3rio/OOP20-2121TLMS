package controller;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import collisions.Collision;
import collisions.ObjectZombieCollision;
import javafx.scene.input.KeyCode;
import view.Level;
import models.WorldObsoleto;
import view.ZombieLevel;
import static com.almasb.fxgl.dsl.FXGL.*;


public class TLMSApp extends GameApplication {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 640;
	private Level zombieLevel;
	
	private final Collision<Entity, Entity> objColZomb = new ObjectZombieCollision();
	
	
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(WIDTH);
		settings.setHeight(HEIGHT);
		
		settings.setApplicationMode(ApplicationMode.DEVELOPER);
	}
	
	@Override
	public void initGame() {
		
		zombieLevel = new ZombieLevel();
		
		getGameWorld().addEntityFactory(new TLMSFactory());
		
		setLevelFromMap(zombieLevel.getLevel());
		//for(int i = 0; i< 5;i++)
			spawn("zombie",500,50);
		
	}
	
	
	 @Override
	 protected void initPhysics() {
		 getPhysicsWorld().addCollisionHandler(new CollisionHandler(TLMSType.DAMAGE_OBJECT, TLMSType.ZOMBIE) {
	            @Override
	            protected void onCollisionBegin(final Entity platform, final Entity zombie) {
	                try {
	                	System.out.println("Collisione Avvenuta");
	                	objColZomb.onCollision(platform, zombie);
	                } catch (Exception e) {
	                  System.out.println("Collisions Object - Zombie, Not Work!");
	                }
	            }
	        });
	 }
	 

	public static void main(String[] args) {
		launch(args);
	}

}
