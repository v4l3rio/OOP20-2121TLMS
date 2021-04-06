package application;


import static com.almasb.fxgl.dsl.FXGL.*;
import java.util.Map;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.ui.UI;

import collisions.BulletZombieCollision;
import collisions.Collision;
import collisions.PlayerZombieCollision;
import factories.TLMSFactory;
import factories.WorldFactory;
import javafx.scene.input.KeyCode;
import model.AnimationComponent;
import model.TLMSType;
import settings.SystemSettingsImpl;
import view.DisplayController;
import settings.SystemSettings;

public class TheLastManStandingApp extends GameApplication {
	
	private static final double WEAPONLENGHT = 25;
	private SystemSettings mySystemSettings = new SystemSettingsImpl();
    private TLMSFactory factory;
    private Entity player;
    
    private final Collision<Entity, Entity> bulletColZombie = new BulletZombieCollision();
	private final Collision<Entity, Entity> playerColZombie = new PlayerZombieCollision();
	
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(mySystemSettings.getWidth());
		settings.setHeight(mySystemSettings.getHeight());
		settings.setTitle(mySystemSettings.getTitle());
		settings.setVersion(mySystemSettings.getVersion());
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
	 }
	
	@Override
	protected void initGame() {
		getGameWorld().addEntityFactory(new WorldFactory());
		factory = new TLMSFactory();
		getGameWorld().addEntityFactory(factory);
		setLevelFromMap("Cemetery.tmx");
		spawn("zombie", 500, 50);
		
		player = spawn("player", 100, 0);
		factory.setPlayer(player);
		
       /* Music gameMusic = FXGL.getAssetLoader().loadMusic("thriller.wav");
    	FXGL.getAudioPlayer().loopMusic(gameMusic);
    	getSettings().setGlobalMusicVolume(0.1);*/
	
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
					inc("playerLife", -1.0);
				} catch (Exception e) {
					System.out.println("Collisions Player - Zombie, Not Working!");
				}
			}
		});
	}

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("playerLife", 2.0);
    }
    
    @Override
    protected void initUI() {
    	DisplayController controller = new DisplayController();
    	UI ui = FXGL.getAssetLoader().loadUI("prova.fxml", controller);
    	getGameScene().addUI(ui);

        controller.getLifeProgressProperty().bind(
            getWorldProperties().doubleProperty("playerLife"));
        controller.getPointsProperty().bind(
            getWorldProperties().intProperty("score").asString("Points: %d"));
        ui.getRoot().setTranslateY(ui.getRoot().getBoundsInLocal().getWidth());
    	
    }

	public static void main(String[] args) {
		launch(args);
	}
}

