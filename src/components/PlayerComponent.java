package components;

import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.util.Duration;
import model.Player;
import model.PlayerImpl;

public class PlayerComponent extends Component {
    
    private PhysicsComponent physics;
    
    private Player player;
    
    private boolean isAttacked = false;
    private boolean isDead = false;
    private boolean isRed = false;

    public PlayerComponent() {
    	this.player = new PlayerImpl();
    }

    @Override
    public void onAdded() { //appena entità viene aggiunto al mondo, viene eseguito
    	
        physics.onGroundProperty().addListener((obs, old, isOnGround) -> { //obs(observer) //old??
        	if (isOnGround) {
	        	player.resetNJumps();
	        }
        });
    }
    
    public Player getPlayer() {
    	return this.player;
    }

    public void moveRight() {
    	
    	getEntity().setScaleX(player.getDimension()); //direzione personaggio
        physics.setVelocityX(player.getSpeed());
    }

    public void moveLeft() {

    	getEntity().setScaleX(-player.getDimension()); //direzione personaggio
        physics.setVelocityX(-player.getSpeed());
    }
    
    public void stop() {
        physics.setVelocityX(0);
    }
    
    public void jump() {
        if (player.getNJumps() > 0) {
	        physics.setVelocityY(player.getJumpHeight());
	        player.decreaseJumps();
        }
    }
    
    public void attacked() {
    	this.isAttacked = true;
    	this.toBlue();
    	this.player.setHealt(player.getHealt()-3);
    	
    	//if(entity.getComponent(ComponentUtils.HEALTH_COMPONENT).getValue()<=0) {
    	if(player.getHealt()<=0) {
    		this.dead();
    	}    	
    	
    	getGameTimer().runOnceAfter(() -> {
    	    this.isAttacked = false;
    	}, Duration.seconds(0.8));
    	
    }
    
    public boolean isAttacked() {
    	return this.isAttacked;
    }
    
    public boolean isDead() {
    	return this.isDead;
    }
    
    public void dead() {
    	this.isDead = true;
    }
    
    public boolean isRed() {
    	return this.isRed;
    }
    
    public void toRed () {
    	this.isRed = true;
    	this.player.setSpeed(350);
    	this.player.setHealt(10);
    	this.player.setMaxJumps(4);
    	this.player.resetNJumps();
    }
    
    public void toBlue() {
    	this.isRed = false;
    	this.player.setSpeed(300);
    	this.player.setMaxJumps(1);
    	this.player.resetNJumps();
    }
}