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
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

import components.ShotTextureComponent;
import components.ComponentUtils;
import components.DamagingComponent;
import components.FirearmComponent;
import components.RandomMovementComponent;
import components.ZombieTextureComponent;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.AnimationComponent;
import model.Beretta92;
import model.Player;
import model.TLMSType;
import model.Zombie;
import model.ZombieRandomTextureDecorator;

public class TLMSFactory implements EntityFactory{
	
	final static int BASICSHOTDMG = 3;
	final static double BASICSHOTSPEED = 500;
	
	//used to keep track of player (ex. direction)
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
                //.bbox(new HitBox(new Point2D(5,5), BoundingShape.circle(12)))
                .bbox(new HitBox(new Point2D(100,80), BoundingShape.box(260, 420))) //coordinate di partenza in alto a destra e dimensione del rettangolo
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

        // this avoidsd player sticking to walls
        //physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder(data)
                .type(TLMSType.PLAYER)
                .bbox(new HitBox(new Point2D(5,5), BoundingShape.circle(12)))
                .bbox(new HitBox(new Point2D(10,25), BoundingShape.box(10, 17)))
                .with(physics)
                .with(new FirearmComponent(new Beretta92()))
                .with(new CollidableComponent(true))
                .with(new HealthIntComponent(10))
                .with(new AnimationComponent())
                .build();
    }
	
	@Spawns("shot")
    public Entity newShot(SpawnData data) {
	 	final double direction = this.player.getScaleX();
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        
        // this avoids player sticking to walls
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        return entityBuilder(data)
                .type(SHOT)
                .bbox(new HitBox(new Point2D(10,25), BoundingShape.box(6, 3)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new ShotTextureComponent(direction))
                .with(new DamagingComponent(player.getComponent(ComponentUtils.FIREARM_COMPONENT)
                		.getCurrentFirearm().getShotDamage()))
                .build();
    }
	
}
