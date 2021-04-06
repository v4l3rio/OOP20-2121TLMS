package controller;

import static com.almasb.fxgl.dsl.FXGL.*;
import static controller.TLMSType.*;


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


import components.DamagingComponent;
import components.RandomMovementComponent;
import components.ZombieTextureComponent;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import models.Zombie;
import models.ZombieRandomTextureDecorator;

public class TLMSFactory implements EntityFactory{
	

	
	@Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        return entityBuilder(data)
                .type(PLATFORM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
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
	

	 
 	@Spawns("damageObject")
    public Entity newDamageObject(SpawnData data) {
	 return entityBuilder(data)
                .type(DAMAGE_OBJECT)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new DamagingComponent(0))
                .with(new CollidableComponent(true))
                .with(new PhysicsComponent())
                .build();
    }
}
