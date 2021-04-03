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
import models.World;
import view.ZombieLevel;
import components.ZombieComponent;
import controller.TLMSType;

import static com.almasb.fxgl.dsl.FXGL.*;


public class TLMSApp extends GameApplication {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 640;
	private Level zombieLevel;
	private World world;
	
	
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
		getGameWorld().addEntityFactory(new TLMSFactory());
		setLevelFromMap(zombieLevel.getLevel());
			spawn("zombie",50,50);
		
	}
	
	protected void initInput() {
		FXGL.getInput().addAction(new UserAction("Shoot") {
			@Override
			protected void onAction() {
				spawn("bullet", 50, 51);
			}
		}, KeyCode.P);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
