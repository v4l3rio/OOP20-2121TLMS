package factories;

import static com.almasb.fxgl.dsl.FXGL.*;

import static model.TLMSType.*;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import components.*;
import javafx.geometry.Point2D;
import model.*;
import components.PlayerComponent;
import components.FirePowerComponent;
import components.DamagingComponent;
import components.RandomMovementComponent;
import components.TextureComponent;
import components.ZombieTextureComponent;
import model.TLMSType;
import model.Player;
import model.PlayerImpl;


/**
 * 
 * @version 3.1
 * This factory creates various types of entities that can be spawned with the "spawn ()" method
 */

public class TLMSFactory implements EntityFactory{
	
	private Entity player;
	
	public void setPlayer(Entity player) {
		this.player = player;
	}

	/**
	 * 
	 * @param data, data to customize the creation of the zombie
	 * @return
	 */
	@Spawns("stupidZombie")
    public Entity newStupidZombie(SpawnData data) {
		
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(16, 38), BoundingShape.box(6, 8)));

        ZombieTextureDecorator zombie = data.get("zombie");

        return entityBuilder(data)
                .type(ZOMBIE)
                .bbox(new HitBox(new Point2D(35,28), BoundingShape.box(65, 125)))
                .with(physics)
                .with(new DamagingComponent(zombie.getDamage()))
                .with(new HealthIntComponent(zombie.getLife()))
                .with(new CollidableComponent(true))
                .with(new RandomMovementComponent(physics, zombie.getSpeed()))
                .with(new ZombieTextureComponent(zombie.getTexture().getTextureMap()))
                .build();
    }
	
	@Spawns("followingZombie")
    public Entity newFollowingZombie(SpawnData data) {
		
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(16, 38), BoundingShape.box(6, 8)));

        ZombieTextureDecorator zombie = data.get("zombie");

        return entityBuilder(data)
                .type(ZOMBIE)
                .bbox(new HitBox(new Point2D(35,28), BoundingShape.box(65, 125)))
                .with(physics)
                .with(new DamagingComponent(zombie.getDamage()))
                .with(new HealthIntComponent(zombie.getLife()))
                .with(new CollidableComponent(true))
                .with(new FollowPlayerComponent(this.player, physics, zombie.getSpeed()))
                .with(new ZombieTextureComponent(zombie.getTexture().getTextureMap()))
                .build();
    }
	
	/**
	 * 
	 * @param data
	 * @return a new entity player
	 */
	@Spawns("player")
    public Entity newPlayer(SpawnData data) {
		PlayerTextures texture = new PlayerTextures(PlayerColor.BLUE);
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(16, 38), BoundingShape.box(6, 8)));

        return entityBuilder(data)
                .type(TLMSType.PLAYER)
                .bbox(new HitBox(new Point2D(15,7), BoundingShape.box(15, 30))) //x collisioni e x piattaforme. Immagini sono incollate sulle hitbox //busto
                .with(physics)
                .with(new FirearmComponent(new Beretta92()))
                .with(new CollidableComponent(true)) //può essere colpito e può atterrare su piattaforme
                .with(new PlayerComponent()) 
                .with(new TextureComponent(texture.getTexture().getTextureMap()))
                .build();
        }

	@Spawns("shot")
    public Entity newShot(SpawnData data) {
		final Firearm currentFirearm = player.getComponent(ComponentUtils.FIREARM_COMPONENT).getCurrentFirearm();
	 	final double direction = this.player.getScaleX();
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        
        return entityBuilder(data)
                .type(SHOT)
                .bbox(new HitBox(new Point2D(50,100), BoundingShape.box(130, 130)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new ShotMovementComponent(direction, currentFirearm.getShotTexture()))
                .with(new DamagingComponent(currentFirearm.getShotDamage()))
                .build();
    }
	
	@Spawns("magmaGun")
    public Entity newMagmaGun(SpawnData data) {
		Firearm magmaGun = new MagmaGun();
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);        
        // this avoids player sticking to walls
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder(data)
                .type(MAGMAGUN)
                .bbox(new HitBox(new Point2D(35,130), BoundingShape.box(160, 100)))
                .with(new CollidableComponent(true))
                .with(physics)
                .with(new PropComponent(magmaGun.getWeaponTexture()))
                .build();
    }

	@Spawns("machineGun")
    public Entity newMachineGun(SpawnData data) {
		Firearm machineGun = new MachineGun();
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return entityBuilder(data)
                .type(MACHINEGUN)
                .bbox(new HitBox(new Point2D(35,130), BoundingShape.box(160, 100)))
                .with(new CollidableComponent(true))
                .with(physics)
                .with(new PropComponent(machineGun.getWeaponTexture()))
                .build();
    }
	
	@Spawns("firePowerUp")
	public Entity newPower(SpawnData data) {
		PhysicsComponent physics = new PhysicsComponent();
		physics.setBodyType(BodyType.DYNAMIC);
		physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(16, 38), BoundingShape.box(6, 80)));
		
		return entityBuilder(data)
				.type(TLMSType.FIREPOWER)
				.bbox(new HitBox(new Point2D(5,5), BoundingShape.circle(12)))
				.with(physics)
				.with(new CollidableComponent(true))
				.with(new FirePowerComponent())
				.build();
	}

}
