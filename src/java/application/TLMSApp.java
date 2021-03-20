package java.application;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.SceneFactory;



public class TLMSApp extends GameApplication{

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setWidth(1280);
        settings.setHeight(720);
        
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }
	
	public static void main(String[] args) {
		launch(args);
	}

		
}


