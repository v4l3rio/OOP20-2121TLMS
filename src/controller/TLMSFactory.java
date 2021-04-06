package controller;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.geto;
import static controller.TLMSType.*;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

import components.AnimationComponent;
import components.BasicBulletComponent;
import components.DamageComponent;
import components.RandomMovementComponent;
import components.ZombieComponent;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.Bullet;
import model.Zombie;

public class TLMSFactory implements EntityFactory{
	
	private Entity player;
	
	/**
	 * 
	 * @param player tracks player
	 */
	protected void setPlayer(Entity player) {
		this.player = player;
	}
	
	@Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        return entityBuilder(data)
                .type(PLATFORM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }
	
    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(16, 38), BoundingShape.box(6, 8)));

        // this avoids player sticking to walls
        //physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder(data)
                .type(TLMSType.PLAYER)
                .bbox(new HitBox(new Point2D(5,5), BoundingShape.circle(12)))
                .bbox(new HitBox(new Point2D(10,25), BoundingShape.box(10, 17)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new AnimationComponent())
                .build();
    }
	
	@Spawns("zombie")
    public Entity newZombie(SpawnData data) {
	 	Zombie zombie = new Zombie(1, 170, new Image("assets/textures/zombie_idle.png"), new Image("assets/textures/zombie_move.png"));
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(16, 38), BoundingShape.box(6, 8)));
        
        // this avoids player sticking to walls
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder(data)
                .type(ZOMBIE)
                .bbox(new HitBox(new Point2D(5,5), BoundingShape.circle(12)))
                .bbox(new HitBox(new Point2D(10,25), BoundingShape.box(10, 17)))
                .with(physics)
                .with(new HealthIntComponent(zombie.getLife()))
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new RandomMovementComponent(physics))
                .with(new ZombieComponent(zombie))
                .build();
    }
	
	@Spawns("bullet")
    public Entity newBullet(SpawnData data) {
	 	Bullet bullet = new Bullet(100, 500, new Image("assets/textures/myBulletsShrunk.png"));
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        
        // this avoids player sticking to walls
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder(data)
                .type(BULLET)
                .bbox(new HitBox(new Point2D(10,25), BoundingShape.box(6, 3)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new BasicBulletComponent(bullet, this.player.getScaleX()))
                .build();
    }
	 
 	@Spawns("damageObject")
    public Entity newDamageObject(SpawnData data) {
	 return entityBuilder(data)
                .type(PLATFORM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new DamageComponent())
                .with(new PhysicsComponent())
                .build();
    }
}
