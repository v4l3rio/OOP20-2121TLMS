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

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import model.Zombie;

import static com.almasb.fxgl.dsl.FXGL.*;


public class TLMSApp extends GameApplication{

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(944);
        settings.setHeight(544);

        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }
	
	private Entity player; //create pla
	
	@Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(Zombie.class).left();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(Zombie.class).stop();
            }
        }, KeyCode.A, VirtualButton.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(Zombie.class).right();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(Zombie.class).stop();
            }
        }, KeyCode.D, VirtualButton.RIGHT);

    }
	
	@Override
	public void initGame() {	
		
		getGameWorld().addEntityFactory(new TLMSFactory());
		
		
	    
		Level level = setLevelFromMap("base_level.tmx");
		getGameWorld().setLevel(level);
		FXGL.setLevelFromMap("base_level.tmx");
		player = null;
		player = spawn("player", 50, 50);
	    set("player", player);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

		
}


