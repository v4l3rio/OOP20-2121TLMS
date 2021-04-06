package factories;

import static com.almasb.fxgl.dsl.FXGL.*;
import static model.TLMSType.*;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

import components.BasicBulletComponent;
import components.DamagingComponent;
import components.RandomMovementComponent;
import components.ZombieTextureComponent;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.AnimationComponent;
import model.Bullet;
import model.TLMSType;
import model.Zombie;
import model.ZombieRandomTextureDecorator;

public class TLMSFactory implements EntityFactory{
	
	private Entity player;
	
	public void setPlayer(Entity player) {
		this.player = player;
	}
	
	@Spawns("zombie")
    public Entity newZombie(SpawnData data) {
	 	
	 	ZombieRandomTextureDecorator zombieTexturized = new ZombieRandomTextureDecorator(new Zombie(10, 170, 3));
	 	
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(16, 38), BoundingShape.box(6, 8)));
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder(data)
                .type(ZOMBIE)
               // .bbox(new HitBox(new Point2D(5,5), BoundingShape.circle(12)))
                .bbox(new HitBox(new Point2D(150,270), BoundingShape.box(150, 230)))
                .with(physics)
                .with(new DamagingComponent(zombieTexturized.getDamage()))
                .with(new HealthIntComponent(zombieTexturized.getLife()))
                .with(new CollidableComponent(true))
                .with(new RandomMovementComponent(physics, zombieTexturized.getSpeed()))
                .with(new ZombieTextureComponent(zombieTexturized.getTexture().getTextureMap()))
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
	
	@Spawns("bullet")
    public Entity newBullet(SpawnData data) {
	 	Bullet bullet = new Bullet(1, 500, new Image("assets/textures/myBulletsShrunk.png"));
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
                .with(new DamagingComponent((int)bullet.getShotDamage()))  //cambiare tutti i danni in double
                .build();
    }
	
}
