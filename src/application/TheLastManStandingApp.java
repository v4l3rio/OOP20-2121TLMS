package application;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.HashMap;
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
import com.almasb.fxgl.time.TimerAction;
import com.almasb.fxgl.ui.UI;

import collisions.ShotZombieCollision;
import components.ComponentUtils;
import controller.ScoreController;
import controller.ScoreControllerImpl;
import controller.VisorController;
import collisions.FirearmCollisionFactoryImpl;
import collisions.PlayerFirePowerCollision;
import collisions.Collision;
import collisions.PlayerZombieCollision;
import components.PlayerComponent;
import components.TextureComponent;
import factories.FirearmFactoryImpl;
import factories.TLMSFactory;
import factories.WorldFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import model.Firearm;
import model.PlayerTexture;
import model.TLMSMusic;
import model.TLMSType;
import model.score.JsonScore;
import settings.SystemSettingsImpl;
import settings.SystemSettings;
import factories.ZombieSpawner;


public class TheLastManStandingApp extends GameApplication {
	
    public static final String PATH_SCORE = "src/assets/score/score.json";
    public static final String PATH_USER = "src/assets/score/userName.json";
    private static final String PATH_MAP = "Cemetery.tmx";
	
	private final SystemSettings mySystemSettings = new SystemSettingsImpl();
    private final ScoreController scoreController = new ScoreControllerImpl();
	private Random random = new Random();
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
	
	/**
	 * manages game inputs therefore connecting each chosen button to his assigned behavior
	 */
	 @Override
	    protected void initInput() {
	    	
	    	getInput().addAction(new UserAction("Left") {
	            @Override
	            protected void onAction() {
	                player.getComponent(PlayerComponent.class).moveLeft();
	            }

	            @Override
	            protected void onActionEnd() {
	                player.getComponent(PlayerComponent.class).stop();
	            }
	        }, KeyCode.A);

	        getInput().addAction(new UserAction("Right") {
	            @Override
	            protected void onAction() {
	                player.getComponent(PlayerComponent.class).moveRight();
	            }

	            @Override
	            protected void onActionEnd() {
	                player.getComponent(PlayerComponent.class).stop();
	            }
	        }, KeyCode.D);

	        getInput().addAction(new UserAction("Jump") {
	            @Override
	            protected void onActionBegin() {
	                player.getComponent(PlayerComponent.class).jump();
	            }
	        }, KeyCode.W);
	        
	        getInput().addAction(new UserAction("Shoot") {
				@Override
				protected void onActionBegin() {
					final Firearm currentFirearm = player.getComponent(ComponentUtils.FIREARM_COMPONENT).getCurrentFirearm();
					//is reloading? can't shoot rn, do nothing
					if(currentFirearm.isReloading()) {
					} else if(currentFirearm.getNAmmo() > 0) {
						// have the shot spawn facing coherently as player, with due distance from it
						spawn("shot", player.getPosition().getX() - AppUtils.SHOT_X_AXIS_FIX 
								+ (AppUtils.GUN_LENGTH*player.getScaleX())
								, player.getPosition().getY() - AppUtils.SHOT_Y_AXIS_FIX);
						currentFirearm.shoot();
					} else {
						reload(currentFirearm);
					}
				}
			}, KeyCode.L);
	        
	        getInput().addAction(new UserAction("Reload") {
	            @Override
	            protected void onActionBegin() {
	            	final Firearm currentFirearm = player.getComponent(ComponentUtils.FIREARM_COMPONENT).getCurrentFirearm();
	            	reload(currentFirearm);
	            }
	        }, KeyCode.R);

	 }
	 /**
	  * Reloads the firearm, keeping it busy for a reload time, while refilling the ammo
	  * @param firearm
	  */
     private void reload(Firearm firearm) {
    	 firearm.setReloading(true);
			spawn("text", new SpawnData(840,150).put("text", "RELOADING"));
			runOnce(()->{
				firearm.reload();
				firearm.setReloading(false);
			}, Duration.seconds(Firearm.RELOAD_TIME));
     }
	
    /**
     * Initializes factories entities and everything necessary to the game world
     */
	@Override
	protected void initGame() {
		getGameWorld().addEntityFactory(new WorldFactory());
		factory = new TLMSFactory();
		getGameWorld().addEntityFactory(factory);
		setLevelFromMap(PATH_MAP);
		double delay = AppUtils.GUN_SPAWN_DELAY;
		spawn("text", new SpawnData(mySystemSettings.getWidth()/3.3, mySystemSettings.getHeight()/8)
				.put("text", "PRESS R FOR AN EARLY RELOAD"));
		ZombieSpawner spawner = new ZombieSpawner();
		spawner.start();
		//spawns a magmaGun after a base+random delay, both incremental
		getGameTimer().runAtInterval(() -> {
			spawn("magmaGun", random.nextInt(mySystemSettings.getWidth()), -100);
			}, Duration.seconds(delay + random.nextInt((int)delay)));
		//spawns a machineGun after a base+random time
		getGameTimer().runAtInterval(() -> {
			spawn("machineGun", random.nextInt(mySystemSettings.getWidth()), -100);
			}, Duration.seconds(delay + random.nextInt((int)delay)));

		player = spawn("player", 1000, 0);
		//sets factory reference of player
		factory.setPlayer(player);
		
		getGameTimer().runAtInterval(() -> {
		    spawn("firePowerUp", random.nextInt(2000), 50);
		}, Duration.seconds(2));
		
		//TLMSMusic music = new TLMSMusic(0.1);
		//getAudioPlayer().loopMusic(music.getMusic());

	}
	
	/**
	 * initialize application physics, e.g. collisions
	 */
	@Override
	protected void initPhysics() {
		
		getPhysicsWorld().addCollisionHandler(new PlayerZombieCollision( TLMSType.PLAYER, TLMSType.ZOMBIE));
		getPhysicsWorld().addCollisionHandler(new ShotZombieCollision( TLMSType.SHOT, TLMSType.ZOMBIE));
		getPhysicsWorld().addCollisionHandler(new FirearmCollisionFactoryImpl()
				.createGunCollision(TLMSType.MAGMAGUN, FirearmFactoryImpl.MAGMA_GUN_DURATION));
		getPhysicsWorld().addCollisionHandler(new FirearmCollisionFactoryImpl()
				.createGunCollision(TLMSType.MACHINEGUN, FirearmFactoryImpl.MACHINE_GUN_DURATION));
		getPhysicsWorld().addCollisionHandler(new PlayerFirePowerCollision(TLMSType.PLAYER, TLMSType.FIREPOWER));

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

