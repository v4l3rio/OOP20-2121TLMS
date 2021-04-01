package application;



/**
 * This class represent the Main class of the JavaFX-based application.
 */


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TiledMap;
import com.almasb.fxgl.input.Input;

import factories.WorldFactory;
import settings.SettingSystemImpl;
import settings.SettingsSystem;

public class TheLastManStandingApp extends GameApplication {
	
	private SettingsSystem mySettings = new SettingSystemImpl();
    
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(mySettings.getWidth());
		settings.setHeight(mySettings.getHeight());
		settings.setTitle(mySettings.getTitle());
		settings.setVersion(mySettings.getVersion());
	}   
	
	@Override
	protected void initGame() {
		FXGL.getGameWorld().addEntityFactory(new WorldFactory());
		FXGL.setLevelFromMap("Cemetery.tmx");
	//	FXGL.getGameWorld().spawn("platform", 5.0, 5.0);
	}
	
	@Override
	protected void initInput() {
		Input input = FXGL.getInput();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

