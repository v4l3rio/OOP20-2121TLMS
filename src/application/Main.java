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
 
public class Main extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
       try {
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

