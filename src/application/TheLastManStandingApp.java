package application;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.ui.UI;
import collisions.ShotZombieCollision;
import collisions.ZombieWallCollision;
import components.ComponentUtils;
import components.GunComponent;
import controller.MapController;
import controller.MapControllerImpl;
import controller.VisorController;
import collisions.GunCollisionFactoryImpl;
import collisions.PlayerFirePowerCollision;
import collisions.PlayerZombieCollision;
import factories.TexturedGunFactoryImpl;
import factories.TLMSFactory;
import factories.WorldFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import model.Gun;
import model.PlayerSpeed;
import model.PlayerSpeedStrategy;
import model.PlayerSpeedTurnsAround;
import model.TLMSMusic;
import model.TLMSType;
import settings.SystemSettingsImpl;
import settings.SystemSettings;
import factories.ZombieSpawner;


public class TheLastManStandingApp extends GameApplication {
	
	private final Random random = new Random();
	private final SystemSettings mySystemSettings = new SystemSettingsImpl();   
	private final MapController mapController = new MapControllerImpl();
    private Entity player;

	@Override
	protected void initSettings(final GameSettings settings) {
		settings.setWidth(mySystemSettings.getWidth());
		settings.setHeight(mySystemSettings.getHeight());
		settings.setTitle(mySystemSettings.getTitle());
		settings.setVersion(mySystemSettings.getVersion());
		settings.setGameMenuEnabled(false);   //disable the default FXGL menu
	}

	
	/**
	 * manages game inputs therefore connecting each chosen button to his assigned behavior.
	 */
	 @Override
	    protected void initInput() {

	    	getInput().addAction(new UserAction("Left") {
	            @Override
	            protected void onAction() {
	            	final PlayerSpeedStrategy strategy = new PlayerSpeed(player);
	                player.getComponent(ComponentUtils.PLAYER_COMPONENT).moveLeft(strategy);
	            }

	            @Override
	            protected void onActionEnd() {
	                player.getComponent(ComponentUtils.PLAYER_COMPONENT).stop();
	            }
	        }, KeyCode.A);

	    	getInput().addAction(new UserAction("TurnLeft") {
	            @Override
	            protected void onActionBegin() {
	            	final PlayerSpeedStrategy strategy = new PlayerSpeedTurnsAround();
	                player.getComponent(ComponentUtils.PLAYER_COMPONENT).moveLeft(strategy);
	            }
	        }, KeyCode.Q);

	        getInput().addAction(new UserAction("Right") {
	            @Override
	            protected void onAction() {
	            	final PlayerSpeedStrategy strategy = new PlayerSpeed(player);
	                player.getComponent(ComponentUtils.PLAYER_COMPONENT).moveRight(strategy);
	            }

	            @Override
	            protected void onActionEnd() {
	                player.getComponent(ComponentUtils.PLAYER_COMPONENT).stop();
	            }
	        }, KeyCode.D);

	        getInput().addAction(new UserAction("TurnRight") {
	            @Override
	            protected void onActionBegin() {
	            	final PlayerSpeedStrategy strategy = new PlayerSpeedTurnsAround();
	                player.getComponent(ComponentUtils.PLAYER_COMPONENT).moveRight(strategy);
	            }
	        }, KeyCode.E);

	        getInput().addAction(new UserAction("Jump") {
	            @Override
	            protected void onActionBegin() {
	                player.getComponent(ComponentUtils.PLAYER_COMPONENT).jump();
	            }
	        }, KeyCode.W);

	        getInput().addAction(new UserAction("Aerodynamics") {
	            @Override
	            protected void onAction() {
	                player.getComponent(ComponentUtils.PLAYER_COMPONENT).aerodynamics();
	                }

	            @Override
	            protected void onActionEnd() {
	                player.getComponent(ComponentUtils.PLAYER_COMPONENT).stop();
	            }
	        }, KeyCode.S);

	        getInput().addAction(new UserAction("Shoot") {
				@Override
				protected void onActionBegin() {
					final var gunComponent = player.getComponent(ComponentUtils.GUN_COMPONENT);
					final Gun currentGun = gunComponent.getCurrentGun();
					//is reloading? can't shoot rn, do nothing
					if (!gunComponent.isReloading()) {
						if (currentGun.getNAmmo() > 0) {
							// have the shot spawn facing coherently as player, with due distance from it
							spawn("shot", player.getPosition().getX() - AppUtils.SHOT_X_AXIS_FIX 
									+ (AppUtils.GUN_LENGTH * player.getScaleX()),
									player.getPosition().getY() - AppUtils.SHOT_Y_AXIS_FIX);
							currentGun.shoot();
						} else {
							reload(gunComponent);
						}	
					}
				}
			}, KeyCode.L);

	        getInput().addAction(new UserAction("Reload") {
	            @Override
	            protected void onActionBegin() {
	            	reload(player.getComponent(ComponentUtils.GUN_COMPONENT));
	            }
	        }, KeyCode.R);

	 }
	 /**
	  * Reloads the gun, keeping it busy for a reload time, while refilling the ammo.
	  * @param gun
	  */
     private void reload(final GunComponent gunComponent) {
    	 gunComponent.setReloading(true);
			spawn("text", new SpawnData(AppUtils.RELOAD_TEXT_X, AppUtils.RELOAD_TEXT_Y)
					.put("text", "RELOADING"));
			runOnce(() -> {
				gunComponent.getCurrentGun().reload();
				gunComponent.setReloading(false);
			}, Duration.seconds(AppUtils.RELOAD_TIME));
     }
	
    /**
     * Initializes factories entities and everything necessary to the game world.
     */
	@Override
	protected void initGame() {
	    final TLMSFactory factory = new TLMSFactory();
		getGameWorld().addEntityFactory(new WorldFactory());
		getGameWorld().addEntityFactory(factory);
		try {
			setLevelFromMap(mapController.readMapTMX());
		} catch (IOException e) {
			System.out.println("Error reading tmx file map");
		}

		final double delay = AppUtils.GUN_SPAWN_DELAY;
		spawn("text", new SpawnData(mySystemSettings.getWidth()/3.3, mySystemSettings.getHeight()/8)
				.put("text", "PRESS R FOR AN EARLY RELOAD"));
		final ZombieSpawner spawner = new ZombieSpawner();

		spawner.start();
		//spawns a magmaGun after a base+random delay, both incremental
		getGameTimer().runAtInterval(() -> {
			spawn("magmaGun", random.nextInt(mySystemSettings.getWidth()), AppUtils.GUN_SPAWN_Y);
			}, Duration.seconds(delay + random.nextInt((int) delay)));
		//spawns a machineGun after a base+random time
		getGameTimer().runAtInterval(() -> {
			spawn("machineGun", random.nextInt(mySystemSettings.getWidth()), AppUtils.GUN_SPAWN_Y);
			}, Duration.seconds(delay + random.nextInt((int) delay)));

		player = spawn("player", AppUtils.MIDDLEXCOORDINATES, AppUtils.ZERO);
		//sets factory reference of player
		factory.setPlayer(player);
		inc("playerLife", (double)player.getComponent(ComponentUtils.PLAYER_COMPONENT).getPlayer().getHealt() / 10);
		
		getGameTimer().runAtInterval(() -> {
		    spawn("firePowerUp", random.nextInt(AppUtils.MAXXCOORDINATES), AppUtils.ZERO);
		}, Duration.seconds(2));
		
		final TLMSMusic music = new TLMSMusic("thriller.mp3", AppUtils.INITIALBGMUSICVOLUME);
		getAudioPlayer().loopMusic(music.getMusic());
		music.noVolume();

	}
	
	/**
	 * initializes application physics, e.g. collisions
	 */
	@Override
	protected void initPhysics() {
		
		getPhysicsWorld().addCollisionHandler(new PlayerZombieCollision( TLMSType.PLAYER, TLMSType.ZOMBIE));
		getPhysicsWorld().addCollisionHandler(new ShotZombieCollision(TLMSType.SHOT, TLMSType.ZOMBIE));
		getPhysicsWorld().addCollisionHandler(new GunCollisionFactoryImpl()
				.createGunCollision(TLMSType.MAGMAGUN, TexturedGunFactoryImpl.MAGMA_GUN_DURATION));
		getPhysicsWorld().addCollisionHandler(new GunCollisionFactoryImpl()
				.createGunCollision(TLMSType.MACHINEGUN, TexturedGunFactoryImpl.MACHINE_GUN_DURATION));
		getPhysicsWorld().addCollisionHandler(new ZombieWallCollision( TLMSType.ZOMBIE, TLMSType.WALL));
		getPhysicsWorld().addCollisionHandler(new PlayerFirePowerCollision(TLMSType.PLAYER, TLMSType.FIREPOWER));

	}

    @Override
    protected void initGameVars(final Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("playerLife",0.0);
        
    }
    
    @Override
    protected void initUI() {
    	final VisorController visorController = new VisorController();
    	final UI ui = getAssetLoader().loadUI(visorController.getFxmlVisor(), visorController);
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

	public static void main(final String[] args) {
		launch(args);
	}
	
}