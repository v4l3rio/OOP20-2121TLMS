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
import javafx.scene.image.Image;
import model.*;
import components.PlayerComponent;
import components.FirePowerComponent;
import components.DamagingComponent;
import components.RandomMovementComponent;
import components.TextureComponent;
import components.ZombieTextureComponent;
import model.TLMSType;


/**
 * This factory creates various types of entities that can be spawned with the "spawn ()" method.
 */

public class TLMSFactory implements EntityFactory{
	
	private static final String GROUND_SENSOR = "GROUND_SENSOR";
	private final TexturedGunFactory gunFactory = new TexturedGunFactoryImpl();
	private Entity player;
	
	public final void setPlayer(final Entity player) {
		this.player = player;
	}

	/**
	 * This method build "stupid" zombie, that moves randomly in the game
	 * @param data - data to customize the creation of the zombie
	 * @return entity - created entity
	 */
	@Spawns("stupidZombie")
    public Entity newStupidZombie(final SpawnData data) {
		
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox(GROUND_SENSOR, new Point2D(16, 38), BoundingShape.box(6, 8)));

        final ZombieTextureDecorator zombie = data.get("zombie");

        return entityBuilder(data)
                .type(ZOMBIE)
                .bbox(new HitBox(new Point2D(35,24), BoundingShape.box(48, 103)))
                .with(physics)
                .with(new DamagingComponent(zombie.getDamage()))
                .with(new HealthIntComponent(zombie.getLife()))
                .with(new CollidableComponent(true))
                .with(new RandomMovementComponent(physics, zombie.getSpeed()))
                .with(new ZombieTextureComponent(zombie.getTexture().getTextureMap(), physics))
                .build();
    }
	
	/**
	 * This method build "follow" zombie, that follows the player in the game
	 * @param data - data to customize the creation of the zombie
	 * @return entity - created entity
	 */
	@Spawns("followingZombie")
    public Entity newFollowingZombie(final SpawnData data) {
		
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox(GROUND_SENSOR, new Point2D(16, 38), BoundingShape.box(6, 8)));

        final ZombieTextureDecorator zombie = data.get("zombie");

        return entityBuilder(data)
                .type(ZOMBIE)
                .bbox(new HitBox(new Point2D(35,24), BoundingShape.box(48, 103)))
                .with(physics)
                .with(new DamagingComponent(zombie.getDamage()))
                .with(new HealthIntComponent(zombie.getLife()))
                .with(new CollidableComponent(true))
                .with(new FollowPlayerComponent(this.player, physics, zombie.getSpeed()))
                .with(new ZombieTextureComponent(zombie.getTexture().getTextureMap(), physics))
                .build();
    }
	
	/**
	 * 
	 * @param data
	 * @return a new entity player
	 */
	@Spawns("player")
    public Entity newPlayer(final SpawnData data) {
		final PlayerTextures texture = new PlayerTextures(PlayerColor.BLUE);
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox(GROUND_SENSOR, new Point2D(30, 12), BoundingShape.box(35, 52)));

        return entityBuilder(data)
                .type(PLAYER)
                .bbox(new HitBox(new Point2D(30,12), BoundingShape.box(35, 52))) 
                .with(physics)
                .with(new GunComponent(new TexturedGunFactoryImpl().getTexturedGun(BERETTA92)))
                .with(new CollidableComponent(true)) 
                .with(new PlayerComponent()) 
                .with(new TextureComponent(texture.getTexture().getTextureMap()))
                .build();
        }

	@Spawns("shot")
	public final Entity newShot(final SpawnData data) {
		final TexturedGun currentGun = player.getComponent(ComponentUtils.GUN_COMPONENT).getCurrentGun();
	 	final double direction = Math.signum(this.player.getScaleX());
	 	final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        return entityBuilder(data)
                .type(SHOT)
                .bbox(new HitBox(new Point2D(10, 28), BoundingShape.box(28, 10)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new ShotMovementComponent(physics, direction, currentGun.getShotspeed(), 
                		new Image(currentGun.getTexture().get(SHOT))))
                .with(new DamagingComponent(currentGun.getDamage()))
                .build();
    }
	
	@Spawns("magmaGun")
    public Entity newMagmaGun(final SpawnData data) {
		final TexturedGun magmaGun = gunFactory.getTexturedGun(MAGMAGUN);
		final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        // this avoids player sticking to walls
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder(data)
                .type(MAGMAGUN)
                .bbox(new HitBox(new Point2D(2, 42), BoundingShape.box(68, 38)))
                .with(new CollidableComponent(true))
                .with(physics)
                .with(new PropComponent(new Image(magmaGun.getTexture().get(GUN))))
                .build();
    }

	@Spawns("machineGun")
	public final Entity newMachineGun(final SpawnData data) {
		final TexturedGun machineGun = gunFactory.getTexturedGun(MACHINEGUN);
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return entityBuilder(data)
                .type(MACHINEGUN)
                .bbox(new HitBox(new Point2D(2, 42), BoundingShape.box(68, 38)))
                .with(new CollidableComponent(true))
                .with(physics)
                .with(new PropComponent(new Image(machineGun.getTexture().get(GUN))))
                .build();
    }
	
	@Spawns("firePowerUp")
	public Entity newPower(final SpawnData data) {
		final PhysicsComponent physics = new PhysicsComponent();
		physics.setBodyType(BodyType.DYNAMIC);
		physics.addGroundSensor(new HitBox(GROUND_SENSOR, new Point2D(16, 38), BoundingShape.box(6, 80)));
		
		return entityBuilder(data)
				.type(FIREPOWER)
				.bbox(new HitBox(new Point2D(10,10), BoundingShape.circle(10)))
				.with(physics)
				.with(new CollidableComponent(true))
				.with(new FirePowerComponent())
				.build();
	}

}
