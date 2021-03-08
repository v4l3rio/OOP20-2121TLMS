package application;



/**
 * This class represent the Main class of the JavaFX-based application.
 */


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Enemy;
import model.Zombie;
import view.ViewManager;
 
public class Main extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
       try {
    	   ViewManager manager = new ViewManager();
    	   primaryStage = manager.getMainStage();
    	   primaryStage.show();
       }
       catch(Exception e){
    	   e.printStackTrace();
    	   
       }
    } 
    public static void main(String[] args) {
        launch(args);
    }   
}

