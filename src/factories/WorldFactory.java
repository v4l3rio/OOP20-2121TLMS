package factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;

public class WorldFactory implements EntityFactory {
	
	@Spawns("platform")
	public Entity newPlatform(SpawnData data) {
		return FXGL.entityBuilder(data)
		   // .type(PLATFORM)
            .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
            .with(new PhysicsComponent())
            .build();
	}

}