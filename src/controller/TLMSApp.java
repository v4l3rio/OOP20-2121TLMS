package controller;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.effects.WobbleEffect;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.view.KeyView;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.time.TimerAction;
import view.Level;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.World;
import view.ZombieLevel;
import components.AnimationComponent;
import components.ZombieComponent;
import controller.TLMSType;

import static com.almasb.fxgl.dsl.FXGL.*;


public class TLMSApp extends GameApplication {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 640;
	private static final double WEAPONLENGHT = 25;
	private Level zombieLevel;
	private TLMSFactory factory;
	private World world;
	private Entity player;
	
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(WIDTH);
		settings.setHeight(HEIGHT);
		
		settings.setApplicationMode(ApplicationMode.DEVELOPER);
	}
	
	@Override
	public void initGame() {
		world = new World();
		zombieLevel = new ZombieLevel();
		factory = new TLMSFactory();
		getGameWorld().addEntityFactory(factory);
		setLevelFromMap(zombieLevel.getLevel());
		spawn("zombie",50,50);
		
		player = spawn("player", 600, 0);
        set("player", player);
        //enables factory to track player
		factory.setPlayer(player);
	}
	
	protected void initInput() {
		
		FXGL.getInput().addAction(new UserAction("Shoot") {
			@Override
			protected void onActionBegin() {
				spawn("bullet", player.getPosition().getX() + (WEAPONLENGHT*player.getScaleX())
						, player.getPosition().getY());
			}
		}, KeyCode.L);
		
    	getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveLeft();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(AnimationComponent.class).stop();
            }
        }, KeyCode.A, VirtualButton.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveRight();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(AnimationComponent.class).stop();
            }
        }, KeyCode.D, VirtualButton.RIGHT);

        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onActionBegin() {
                player.getComponent(AnimationComponent.class).jump();
            }
        }, KeyCode.W, VirtualButton.A);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
