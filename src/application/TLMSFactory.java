package application;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.geto;

import com.almasb.fxgl.core.util.LazyValue;

import static application.TLMSType.*;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.dsl.components.RandomMoveComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

import components.ZombieComponent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

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
	                .with(new CollidableComponent(true))
	                .with(new IrremovableComponent())
	                .with(new ZombieComponent())
	                .build();
	    }
}
