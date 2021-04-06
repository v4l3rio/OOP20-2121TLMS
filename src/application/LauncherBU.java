package application;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.CollisionHandler;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.AnimationComponent;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class LauncherBU extends GameApplication {
	
	private Entity player;
	
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(640);
        settings.setTitle("2121");
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
        
//        getInput().addAction(new UserAction("Transformation") {
//            @Override
//            protected void onActionBegin() {
//                player.getComponent(AnimationComponent.class).transformation();
//            }
//        }, KeyCode.O);
    	
    	//old movement
//    	FXGL.getInput().addAction(new UserAction("Right") {
//            @Override
//            protected void onAction() {
//                player.getComponent(AnimationComponent.class).moveRight();
//            }
//        }, KeyCode.D);
//
//        FXGL.getInput().addAction(new UserAction("Left") {
//            @Override
//            protected void onAction() {
//                player.getComponent(AnimationComponent.class).moveLeft();
//            }
//        }, KeyCode.A);
//        
//        onKey(KeyCode.W, () -> {
//            player.translateY(-5); // move up 5 pixels
//            //inc("pixelsMoved", +5);
//        });
//
//        onKey(KeyCode.S, () -> {
//            player.translateY(5); // move down 5 pixels
//            //inc("pixelsMoved", +5);
//        });
    	
    	/////
    	
//        onKey(KeyCode.D, () -> {
//            //player.translateX(5); // move right 5 pixels
//            //inc("pixelsMoved", +5);
//        });
//
//        onKey(KeyCode.A, () -> {
//            //player.translateX(-5); // move left 5 pixels
//            //inc("pixelsMoved", -5);
//        });

        
    }

//    @Override
//    protected void initGameVars(Map<String, Object> vars) {
//        vars.put("pixelsMoved", 0);
//    }

//    @Override
//    protected void onPreInit() {
////    	Music gameMusic = FXGL.getAssetLoader().loadMusic("thriller.wav");
////    	FXGL.getAudioPlayer().loopMusic(gameMusic);
//    	
////        getSettings().setGlobalMusicVolume(0.25);
////        loopBGM("BGM_dash_runner.wav");
//    }

    @Override
    protected void initGame() {
    	
    	getGameWorld().addEntityFactory(new TLMSFactory());

        player = null;
        
        Level level = setLevelFromMap("base_level.tmx");
		getGameWorld().setLevel(level);
		FXGL.setLevelFromMap("base_level.tmx");
		
		player = spawn("player", 200, 0);
        set("player", player);
        
        Music gameMusic = FXGL.getAssetLoader().loadMusic("thriller.wav");
    	FXGL.getAudioPlayer().loopMusic(gameMusic);
    	getSettings().setGlobalMusicVolume(0.1);
        
    	//quadrato blu
//        player = entityBuilder()
//                .at(300, 300)
//                .view("JohnTravoltaIdle.png")
//                .buildAndAttach();
    	//con collisioni
//        player = FXGL.entityBuilder()
//                .type(TLMSType.PLAYER)
//                .at(0, 0)
//                .viewWithBBox("JohnTravoltaIdle.png")
//                .with(new CollidableComponent(true))
//                .buildAndAttach();
//        FXGL.entityBuilder() //monetina
//        .type(TLMSType.ZOMBIE)
//        .at(500, 200)
//        .viewWithBBox(new Circle(15, 15, 15, Color.YELLOW))
//        .with(new CollidableComponent(true))
//        .buildAndAttach();
    	
      
    	
    	//animation //memo: sprite sheet images
//    	player = FXGL.entityBuilder()
//                .at(200, 200)
//                .with(new AnimationComponent())
//                .buildAndAttach();   	

        // player must be spawned after call to nextLevel, otherwise player gets removed
        // before the update tick _actually_ adds the player to game world
        

       // spawn("background");

//        Viewport viewport = getGameScene().getViewport();
//        viewport.setBounds(-1500, 0, 250 * 70, getAppHeight());
//        viewport.bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
//        viewport.setLazy(true);

    }
    
//    @Override
//    protected void initPhysics() { //per collisioni
//        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(TLMSType.PLAYER, TLMSType.ZOMBIE) {
//
//            // order of types is the same as passed into the constructor
//            @Override
//            protected void onCollisionBegin(Entity player, Entity coin) {
//                coin.removeFromWorld();
//            }
//        });
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
