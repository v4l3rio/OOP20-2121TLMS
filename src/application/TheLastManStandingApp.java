package application;



/**
 * This class represent the Main class of the JavaFX-based application.
 */


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
import java.util.Map;

public class TheLastManStandingApp extends GameApplication {
    
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(600);
		settings.setHeight(600);
		settings.setTitle("2021 The Last Man Standing");
		settings.setVersion("1.0");
	}   
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Entity player;
	
	@Override
	protected void initGame() {
		player = FXGL.entityBuilder()
				.at(300, 300)
				.view(new Rectangle(25, 25, Color.BLUE))
				.buildAndAttach();
					
	}
	
	@Override
	protected void initInput() {
		Input input = FXGL.getInput();
		
	}
}

