package application;



import java.util.Map;

/**
 * This class represent the Main class of the JavaFX-based application.
 */


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.UI;

import factories.WorldFactory;
import settings.SystemSettingsImpl;
import view.DisplayController;
import settings.SystemSettings;

public class TheLastManStandingApp extends GameApplication {
	
	private SystemSettings mySystemSettings = new SystemSettingsImpl();
	
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(mySystemSettings.getWidth());
		settings.setHeight(mySystemSettings.getHeight());
		settings.setTitle(mySystemSettings.getTitle());
		settings.setVersion(mySystemSettings.getVersion());
	}   
	
	@Override
	protected void initGame() {
		FXGL.getGameWorld().addEntityFactory(new WorldFactory());
		FXGL.setLevelFromMap("Cemetery.tmx");
		//Music gameMusic = FXGL.getAssetLoader().loadMusic(myAudioSettings.getMusicGame());
		//FXGL.getAudioPlayer().loopMusic(gameMusic);
	
	}
	
	@Override
	protected void initInput() {
	
	}
	

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("playerLife", 1.0);
    }
    
    @Override
    protected void initUI() {
    	DisplayController controller = new DisplayController();
    	UI ui = FXGL.getAssetLoader().loadUI("prova.fxml", controller);
    	FXGL.getGameScene().addUI(ui);

        controller.getLifeProgressProperty().bind(
            FXGL.getWorldProperties().doubleProperty("playerLife"));
        controller.getPointsProperty().bind(
            FXGL.getWorldProperties().intProperty("score").asString("Points: %d"));
        ui.getRoot().setTranslateY(ui.getRoot().getBoundsInLocal().getWidth());
    	
    }

	public static void main(String[] args) {
		launch(args);
	}
}

