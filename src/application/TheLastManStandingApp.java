package application;

import static com.almasb.fxgl.dsl.FXGL.*;
import java.util.Map;
import java.util.Random;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.ui.UI;

import collisions.ShotZombieCollision;
import components.ComponentUtils;
import controller.ScoreController;
import controller.ScoreControllerImpl;
import controller.VisorController;
import collisions.FirearmCollisionFactoryImpl;
import collisions.PlayerZombieCollision;
import factories.TLMSFactory;
import factories.WorldFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import model.Firearm;
import model.TLMSType;
import model.score.JsonScore;
import settings.SystemSettingsImpl;
import settings.SystemSettings;
import components.AnimationComponent;
import factories.ZombieSpawner;

import view.DisplayController;

public class TheLastManStandingApp extends GameApplication {
	
    public static final String PATH_SCORE = "src/assets/score/score.json";
    public static final String PATH_USER = "src/assets/score/userName.json";
    private static final String PATH_MAP = "Cemetery.tmx";
	private static final double WEAPONLENGHT = 25;
	private int GunSpawnDelay = 5;
	private Random random = new Random();
    private boolean isRecharging = false;
	private static final int MAGMAGUNDURATION = 5;
	private static final int MACHINEGUNDURATION = 6;
	private final SystemSettings mySystemSettings = new SystemSettingsImpl();
    private final ScoreController scoreController = new ScoreControllerImpl();    
//    private final Collision<Entity, Entity> bulletColZombie = new ShotZombieCollision();
//	private final Collision<Entity, Entity> playerColZombie = new PlayerZombieCollision();
//	private final Collision<Entity, Entity> playerMagmaGun = new FirearmCollisionFactoryImpl()
//			.createGunCollision(TLMSType.MAGMAGUN, MAGMAGUNDURATION);
//	private final Collision<Entity, Entity> playerMachineGun = new FirearmCollisionFactoryImpl()
//			.createGunCollision(TLMSType.MACHINEGUN, MACHINEGUNDURATION);
    private TLMSFactory factory;
    private Entity player;

    
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(mySystemSettings.getWidth());
		settings.setHeight(mySystemSettings.getHeight());
		settings.setTitle(mySystemSettings.getTitle());
		settings.setVersion(mySystemSettings.getVersion());
		settings.setGameMenuEnabled(false);   //disable the default FXGL menu

	}   
	
	 @Override
	    protected void initInput() {
	    	
	    	getInput().addAction(new UserAction("Left") {
	            @Override
	            protected void onAction() {
	                player.getComponent(AnimationComponent.class).moveLeft();
	            }

	            @Override
	            protected void onActionEnd() {
	                player.getComponent(AnimationComponent.class).stop();
	            }
	        }, KeyCode.A);

	        getInput().addAction(new UserAction("Right") {
	            @Override
	            protected void onAction() {
	                player.getComponent(AnimationComponent.class).moveRight();
	            }

	            @Override
	            protected void onActionEnd() {
	                player.getComponent(AnimationComponent.class).stop();
	            }
	        }, KeyCode.D);

	        getInput().addAction(new UserAction("Jump") {
	            @Override
	            protected void onActionBegin() {
	                player.getComponent(AnimationComponent.class).jump();
	            }
	        }, KeyCode.W);
	        
	        getInput().addAction(new UserAction("Shoot") {
				@Override
				protected void onActionBegin() {
					final Firearm currentFirearm = player.getComponent(ComponentUtils.FIREARM_COMPONENT).getCurrentFirearm();
					//is recharging? can't shoot rn, do nothing
					if(isRecharging) {
					} else if(currentFirearm.getNAmmo() > 0) {
						// have the shot spawn facing coherently as player, with due distance from it
						spawn("shot", player.getPosition().getX() + (WEAPONLENGHT*player.getScaleX())
								, player.getPosition().getY());
						currentFirearm.decAmmo();
					} else {
						isRecharging = true;
						runOnce(()->{
							currentFirearm.recharge();
							isRecharging = false;
						}, Duration.seconds(2));
					}
				}
			}, KeyCode.L);
	        
	        getInput().addAction(new UserAction("Reload") {
	            @Override
	            protected void onActionBegin() {
	            	final Firearm currentFirearm = player.getComponent(ComponentUtils.FIREARM_COMPONENT).getCurrentFirearm();
	            	spawn("text", new SpawnData(750,150).put("text", "RELOAD"));
	            	isRecharging = true; 
	        		runOnce(() -> { 
	        			currentFirearm.recharge(); 
	        			isRecharging = false; 
	        			}, Duration.seconds(2)
	        		); 
	            }
	        }, KeyCode.R);

			
			
			 
	 }

	
	@Override
	protected void initGame() {
		getGameWorld().addEntityFactory(new WorldFactory());
		factory = new TLMSFactory();
		getGameWorld().addEntityFactory(factory);
		setLevelFromMap(PATH_MAP);

		ZombieSpawner spawner = new ZombieSpawner();
		spawner.start();
		//spawn a magmaGun after a base+random delay, both incremental
		getGameTimer().runAtInterval(() -> {
			spawn("magmaGun", random.nextInt(mySystemSettings.getWidth()), -100);
			}, Duration.seconds(GunSpawnDelay + random.nextInt(++GunSpawnDelay)));
		getGameTimer().runAtInterval(() -> {
			//spawn a machineGun after a base+random time
			spawn("machineGun", random.nextInt(mySystemSettings.getWidth()), -100);
			}, Duration.seconds(GunSpawnDelay + random.nextInt(++GunSpawnDelay)));
		player = spawn("player", 1000, 0);
		factory.setPlayer(player);
		
//		Music gameMusic = FXGL.getAssetLoader().loadMusic("thriller.wav");
//    	FXGL.getAudioPlayer().loopMusic(gameMusic);
//    	getSettings().setGlobalMusicVolume(0.1);
	}
	
	@Override
	protected void initPhysics() {
//		getPhysicsWorld().addCollisionHandler(new CollisionHandler(TLMSType.SHOT, TLMSType.ZOMBIE) {
//			@Override
//			protected void onCollisionBegin(final Entity shot, final Entity zombie) {
//				try {
//					System.out.println("Collisione Avvenuta");
//					bulletColZombie.onCollision(shot, zombie);
//					inc("score", +1);
//					spawn("zombiePoints", new SpawnData(zombie.getX(),zombie.getY()).put("zombiePoints", "+1"));
//				} catch (Exception e) {
//					System.out.println("Collisions Shot - Zombie, Not Work!");
//				}
//			}
//			
//		});
//
//		getPhysicsWorld().addCollisionHandler(new CollisionHandler(TLMSType.PLAYER, TLMSType.ZOMBIE) {
//			@Override
//			protected void onCollisionBegin(final Entity player, final Entity zombie) {
//				try {
//					System.out.println("Collisione Avvenuta");
//					playerColZombie.onCollision(player, zombie);
//					inc("playerLife", -0.1);
//					if(player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
//						player.removeFromWorld();
//						scoreController.updateScore(
//								new JsonScore(getWorldProperties().intProperty("score").get())
//						);
//						gameOver();
//					}
//				} catch (Exception e) {
//					System.out.println("Collisions Player - Zombie, Not Working!");
//				}
//			}
//		});
//		
//		getPhysicsWorld().addCollisionHandler(new CollisionHandler(TLMSType.PLAYER, TLMSType.MAGMAGUN) {
//			@Override
//			protected void onCollisionBegin(final Entity player, final Entity magmaGun) {
//				try {
//					playerMagmaGun.onCollision(player, magmaGun);
//				} catch (Exception e) {
//					System.out.println("Collisions Player - MagmaGun, Not Working!");
//				}
//			}
//		});
//		
//		getPhysicsWorld().addCollisionHandler(new CollisionHandler(TLMSType.PLAYER, TLMSType.MACHINEGUN) {
//			@Override
//			protected void onCollisionBegin(final Entity player, final Entity machineGun) {
//				try {
//					playerMachineGun.onCollision(player, machineGun);
//				} catch (Exception e) {
//					System.out.println("Collisions Player - MachineGun, Not Working!");
//				}
//			}
//		});
		
		getPhysicsWorld().addCollisionHandler(new PlayerZombieCollision( TLMSType.PLAYER, TLMSType.ZOMBIE));
		getPhysicsWorld().addCollisionHandler(new ShotZombieCollision( TLMSType.SHOT, TLMSType.ZOMBIE));
		getPhysicsWorld().addCollisionHandler(new FirearmCollisionFactoryImpl()
				.createGunCollision(TLMSType.MAGMAGUN, MAGMAGUNDURATION));
		getPhysicsWorld().addCollisionHandler(new FirearmCollisionFactoryImpl()
				.createGunCollision(TLMSType.MACHINEGUN, MACHINEGUNDURATION));
	}

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("playerLife", 1.0);
    }
    
    @Override
    protected void initUI() {
    	VisorController visorController = new VisorController();
    	UI ui = getAssetLoader().loadUI(visorController.getFxmlVisor(), visorController);
    	getGameScene().addUI(ui);
    	visorController.getLifeProgressProperty().bind(
            getWorldProperties()
            .doubleProperty("playerLife")
        );
    	visorController.getPointsProperty().bind(
            getWorldProperties()
            .intProperty("score")
            .asString("Points: %d")
        );  	

    }

	public static void main(String[] args) {
		launch(args);
	}
	
}

