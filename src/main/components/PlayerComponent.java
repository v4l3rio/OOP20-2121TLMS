package main.components;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.util.Duration;
import main.model.player.Player;
import main.model.player.PlayerColor;
import main.model.player.PlayerImpl;
import main.model.player.PlayerSpeedStrategy;

public class PlayerComponent extends Component {
    
    private PhysicsComponent physics;
    
    private final Player player;
    
    private boolean isAttacked;
    private boolean isDead;

    /**
     * generation of all the initial values of the player
     */
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
    
    /**
     * 
     * @return player with all his values
     */
    public Player getPlayer() {
    	return this.player;
    }

    /**
     * player right movement
     * @param strategy
     */
    public void moveRight(final PlayerSpeedStrategy strategy) {
    	getEntity().setScaleX(player.getDimension()); //direzione personaggio
        physics.setVelocityX(strategy.getVelocity());
    }

    /**
     * player left movement
     * @param strategy
     */
    public void moveLeft(final PlayerSpeedStrategy strategy) {
    	getEntity().setScaleX(-player.getDimension()); //direzione personaggio
        physics.setVelocityX(-strategy.getVelocity());
    }
    
    /**
     * this method set player's speed to 0
     */
    public void stop() {
        physics.setVelocityX(0);
    }
    
    /**
     * management of player jumps
     */
    public void jump() {
        if (player.getNJumps() > 0) {
	        physics.setVelocityY(player.getJumpHeight());
	        player.decreaseJumps();
        }
    }
    
    /**
     * the ability to land faster
     */
    public void aerodynamics() {
    	if (this.player.getColor()==PlayerColor.RED) {
    		physics.setVelocityY(-player.getJumpHeight()*2);
    	}
    }
    
    /**
     * 
     * @return true if he is attacked
     */
    public boolean isAttacked() {
    	return this.isAttacked;
    }
    
    /**
     * 
     * @return true if he is dead
     */
    public boolean isDead() {
    	return this.isDead;
    }
    
    /**
     * @param damage
     */
    public void attacked(final int damage) {
    	this.isAttacked = true;
    	
    	this.player.setHealt(player.getHealt()-damage);

    	if(player.getHealt()<=0) {
    		this.dead();
    	}    	
    	
    	getGameTimer().runOnceAfter(() -> {
    	    this.isAttacked = false;
    	}, Duration.seconds(0.8));
    	
    }
    
    /**
     * this method set a boolean, telling the player's death
     */
    private void dead() {
    	this.isDead = true;
    }
  
}