package application;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.PlayerComponent;

import java.util.Map;

public class Launcher2 extends GameApplication{
	

	public static void main(String[] args) {
		launch(args);
	}
	
	private Entity player;

	@Override
	protected void initSettings(GameSettings settings) {
//		settings.setWidth(600);
//	    settings.setHeight(600);
//	    settings.setTitle("Basic Game App");
//	    settings.setVersion("0.1");			
	}

	@Override
	protected void initGame() {
	    player = FXGL.entityBuilder()
	            .at(200, 200)
	            .with(new PlayerComponent())
	            .buildAndAttach();
	}
	
	@Override
	protected void initInput() {
	    FXGL.getInput().addAction(new UserAction("Right") {
	    	
	        @Override
	        protected void onAction() {
	            player.getComponent(PlayerComponent.class).right();
	        }
	    }, KeyCode.D);

	    FXGL.getInput().addAction(new UserAction("Left") {
	    	
	        @Override
	        protected void onAction() {
	            player.getComponent(PlayerComponent.class).left();
	        }
	    }, KeyCode.A);
	}
}