package application;


import static com.almasb.fxgl.dsl.FXGL.*;
import java.util.Map;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.ui.UI;

import collisions.BulletZombieCollision;
import collisions.Collision;
import collisions.PlayerZombieCollision;
import components.ComponentUtils;
import controller.ScoreController;
import controller.ScoreControllerImpl;
import controller.VisorController;
import factories.TLMSFactory;
import factories.WorldFactory;
import javafx.scene.input.KeyCode;
import model.AnimationComponent;
import model.TLMSType;
import model.score.JsonScore;
import settings.SystemSettingsImpl;
import settings.SystemSettings;

public class TheLastManStandingApp extends GameApplication {
	
    public static final String PATH_SCORE = "src/assets/score/score.json";
    public static final String PATH_USER = "src/assets/score/userName.json";
    private static final String PATH_MAP = "Cemetery.tmx";
	private static final double WEAPONLENGHT = 25;
	private final SystemSettings mySystemSettings = new SystemSettingsImpl();
    private final ScoreController scoreController = new ScoreControllerImpl();    
    private final Collision<Entity, Entity> bulletColZombie = new BulletZombieCollision();
	private final Collision<Entity, Entity> playerColZombie = new PlayerZombieCollision();
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
					spawn("bullet", player.getPosition().getX() + (WEAPONLENGHT*player.getScaleX())
							, player.getPosition().getY());
				}
			}, KeyCode.L);
	        
	        getInput().addAction(new UserAction("Reload") {
	            @Override
	            protected void onActionBegin() {
	            	spawn("text", new SpawnData(750,150).put("text", "RELOAD"));
	            }
	        }, KeyCode.R);
	 }
	
	@Override
	protected void initGame() {
		getGameWorld().addEntityFactory(new WorldFactory());
		factory = new TLMSFactory();
		getGameWorld().addEntityFactory(factory);
		setLevelFromMap(PATH_MAP);
		for(int i =0;i<3;i++)
			spawn("zombie", 500, 50);
		
		player = spawn("player", 100, 0);
		factory.setPlayer(player);
		
		Music gameMusic = FXGL.getAssetLoader().loadMusic("thriller.wav");
    	FXGL.getAudioPlayer().loopMusic(gameMusic);
    	getSettings().setGlobalMusicVolume(0.1);
		
	}
	
	@Override
	protected void initPhysics() {
		getPhysicsWorld().addCollisionHandler(new CollisionHandler(TLMSType.BULLET, TLMSType.ZOMBIE) {
			@Override
			protected void onCollisionBegin(final Entity bullet, final Entity zombie) {
				try {
					System.out.println("Collisione Avvenuta");
					bulletColZombie.onCollision(bullet, zombie);
					inc("score", +1);
					spawn("zombiePoints", new SpawnData(zombie.getX(),zombie.getY()).put("zombiePoints", "+1"));
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
					inc("playerLife", -0.1);
					if(player.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
						player.removeFromWorld();
						scoreController.updateScore(
								new JsonScore(getWorldProperties().intProperty("score").get())
						);
						gameOver();
					}
				} catch (Exception e) {
					System.out.println("Collisions Player - Zombie, Not Working!");
				}
			}
		});
	}
	
	private void gameOver() {
        getDialogService().showMessageBox("Game Over. Press OK to exit", getGameController()::exit);
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

