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
	private static final double GUN_LENGHT = 30;
	private static final double SHOT_Y_AXIS_FIX = 60;
	private static final double SHOT_X_AXIS_FIX = 48;
	
	private final SystemSettings mySystemSettings = new SystemSettingsImpl();
    private final ScoreController scoreController = new ScoreControllerImpl();
	private int GunSpawnDelay = 7;
	private Random random = new Random();
    private boolean isReloading = false;
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
					if(isReloading) {
					} else if(currentFirearm.getNAmmo() > 0) {
						// have the shot spawn facing coherently as player, with due distance from it
						spawn("shot"
								, player.getPosition().getX() - SHOT_X_AXIS_FIX + (GUN_LENGHT*player.getScaleX())
								, player.getPosition().getY() - SHOT_Y_AXIS_FIX);
						currentFirearm.shoot();
					} else {
						isReloading = true;
						spawn("text", new SpawnData(840,150).put("text", "RELOADING"));
						runOnce(()->{
							currentFirearm.reload();
							isReloading = false;
						}, Duration.seconds(FirearmFactoryImpl.RELOAD_TIME));
					}
				}
			}, KeyCode.L);
	        
	        getInput().addAction(new UserAction("Reload") {
	            @Override
	            protected void onActionBegin() {
	            	final Firearm currentFirearm = player.getComponent(ComponentUtils.FIREARM_COMPONENT).getCurrentFirearm();
	            	spawn("text", new SpawnData(840,150).put("text", "RELOADING"));
	            	isReloading = true;
	        		runOnce(() -> { 
	        			currentFirearm.reload(); 
	        			isReloading = false; 
	        			}, Duration.seconds(FirearmFactoryImpl.RELOAD_TIME)
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
		spawn("text", new SpawnData(mySystemSettings.getWidth()/3.3, mySystemSettings.getHeight()/8)
				.put("text", "PRESS R FOR AN EARLY RELOAD"));
		ZombieSpawner spawner = new ZombieSpawner();
		spawner.start();
		//spawn a magmaGun after a base+random delay, both incremental
		getGameTimer().runAtInterval(() -> {
			spawn("magmaGun", random.nextInt(mySystemSettings.getWidth()), -100);
			}, Duration.seconds(GunSpawnDelay + random.nextInt(GunSpawnDelay)));
		getGameTimer().runAtInterval(() -> {
			//spawn a machineGun after a base+random time
			spawn("machineGun", random.nextInt(mySystemSettings.getWidth()), -100);
			}, Duration.seconds(GunSpawnDelay + random.nextInt(GunSpawnDelay)));
		player = spawn("player", 1000, 0);
		factory.setPlayer(player);
		
		getGameTimer().runAtInterval(() -> {
		    spawn("firePowerUp", random.nextInt(2000), 50);
		}, Duration.seconds(2));
		
		//TLMSMusic music = new TLMSMusic(0.1);
		//getAudioPlayer().loopMusic(music.getMusic());

	}
	
	@Override
	protected void initPhysics() {
		
		getPhysicsWorld().addCollisionHandler(new PlayerZombieCollision( TLMSType.PLAYER, TLMSType.ZOMBIE));
		getPhysicsWorld().addCollisionHandler(new ShotZombieCollision( TLMSType.SHOT, TLMSType.ZOMBIE));
		getPhysicsWorld().addCollisionHandler(new FirearmCollisionFactoryImpl()
				.createGunCollision(TLMSType.MAGMAGUN, FirearmFactoryImpl.MGM_GUN_DURATION));
		getPhysicsWorld().addCollisionHandler(new FirearmCollisionFactoryImpl()
				.createGunCollision(TLMSType.MACHINEGUN, FirearmFactoryImpl.MCH_GUN_DURATION));
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

