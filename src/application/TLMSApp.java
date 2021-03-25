package application;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.view.KeyView;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.time.TimerAction;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Zombie;
import application.TLMSType;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class TLMSApp extends GameApplication {
	
	

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(1280);
		settings.setHeight(640);

		settings.setApplicationMode(ApplicationMode.DEVELOPER);
	}

	private Entity zombie;
	int countOfZombie=0;
	private static final int NUMOFZOMBIE = 10;
	/*
	private double levelTime=0.0;
	private int seconds=0;
	private int nextDirection=0;
	Random rnd = new Random();
	
	
	 * @Override protected void onUpdate(double tpf) { levelTime= levelTime+tpf;
	 * nextDirection = rnd.nextInt(3);
	 * 
	 * switch (nextDirection) { case 0: if(levelTime>seconds) {
	 * zombie.getComponent(Zombie.class).left(); seconds++; } break; case 1:
	 * if(levelTime>seconds) { zombie.getComponent(Zombie.class).right(); seconds++;
	 * } break; case 2: if(levelTime>seconds) {
	 * zombie.getComponent(Zombie.class).stop(); seconds++; } break;
	 * 
	 * default: if(levelTime>seconds) { zombie.getComponent(Zombie.class).stop();
	 * seconds++; } break;
	 * 
	 * }
	 * 
	 * }
	 */
	
	private void spawnEnemy() {
		
		// IMPORTANTE! gli zombie vanno aggiunti a "numOfZombie"
		
		Iterator<Entity> numOfZombie = FXGL.getGameWorld().getEntitiesByType(TLMSType.ZOMBIE).iterator();
		
		while(numOfZombie.hasNext()) {
			countOfZombie++;
			numOfZombie.next();
		}
		while(countOfZombie<NUMOFZOMBIE) {
			spawn("zombie", 50, 50);
			countOfZombie++;
		}
	
	}


	@Override
	public void initGame() {

		getGameWorld().addEntityFactory(new TLMSFactory());

		Level level = setLevelFromMap("base_level.tmx");
		getGameWorld().setLevel(level);
		FXGL.setLevelFromMap("base_level.tmx");
		spawnEnemy();
		/*
		 * zombie = null;
		 * 
		 * zombie = spawn("zombie", 50, 50);
		 * 
		 * set("zombie", zombie);
		 */



	}

	public static void main(String[] args) {
		launch(args);

	}

}
